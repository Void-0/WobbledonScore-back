import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import spark.Service;
import java.util.*;
import java.util.concurrent.*;

/**
 * Main application class that runs the simulation.
 */
public class AppMain implements Runnable {
    // this map is shared between sessions and threads, so it needs to be thread-safe
    private final Map<Session, String> userUsernameMap;
    private int nextUserNumber;
    private Match match;
    private final DatabaseMock mockDB;

    /**
     * Main application default constructor.
     */
    public AppMain() {
        userUsernameMap = new ConcurrentHashMap<>();
        nextUserNumber = 1;
        match = null;
        mockDB = new DatabaseMock();
    }

    /**
     * Application main method.
     *
     * @param args app args.
     */
    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        appMain.run();
    }

    /**
     * Initialisation and simulation start.
     */
    @Override
    public void run() {
        initializeHttpService();
        startMatchSimulation(0, 1, 5000, 300);
    }

    /**
     * Initialization of match simulation including match parameters and scheduling.
     *
     * @param playerA_ID the DB id of the first player.
     * @param playerB_ID the DB id of the second player.
     * @param initialDelay the initial simulation delay.
     * @param period interval at which the simulation progresses.
     */
    private void startMatchSimulation(int playerA_ID, int playerB_ID, int initialDelay, int period) {
        match = new Match(
                mockDB.getPlayerList().get(playerA_ID),
                mockDB.getPlayerList().get(playerB_ID)
        );

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if(!match.hasEnded()) {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    match.grantPointA();
                } else {
                    match.grantPointB();
                }

                broadcastScore(match);
            }
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * Http service related initialization including CRUD and websocket.
     */
    private void initializeHttpService() {
        Gson gson = new Gson();
        Service http = Service.ignite();

        http.port(9999);

        // declaring websocket to watch the score
        http.webSocket("/score", new ScoreWebSocketHandler());

        http.before((request, response) -> {
            response.type("application/json");
        });

        http.get("/players", (req, res) -> mockDB.getPlayerList(), gson::toJson);
    }

    /**
     * Sends current match score to all connected user trough websockets.
     *
     * @param match the current match whose score the users are following.
     */
    public void broadcastScore(Match match) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(new Gson().toJson(match));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Web socket related operation handling.
     */
    @WebSocket
    public class ScoreWebSocketHandler {

        /**
         * Saves the new session user on websocket connect and sends current match score.
         *
         * @param user the new session user.
         * @throws Exception connection exception.
         */
        @OnWebSocketConnect
        public void onConnect(Session user) throws Exception {
            String username = "User" + nextUserNumber++;
            userUsernameMap.put(user, username);
            broadcastScore(match);
        }

        /**
         * Removes user on websocket close.
         *
         * @param user the session user.
         * @param statusCode the status code.
         * @param reason the close reson.
         */
        @OnWebSocketClose
        public void onClose(Session user, int statusCode, String reason) {
            userUsernameMap.remove(user);
        }

        /**
         * Resends score through websockets at user demand.
         *
         * @param user session user.
         * @param message received user message.
         */
        @OnWebSocketMessage
        public void onMessage(Session user, String message) {
            broadcastScore(match);
        }

    }
}
