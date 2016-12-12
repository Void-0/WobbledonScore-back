import com.google.gson.Gson;
import spark.Service;
import org.eclipse.jetty.websocket.api.*;
import spark.Spark;

import java.util.*;
import java.util.concurrent.*;

public class AppMain {
    // this map is shared between sessions and threads, so it needs to be thread-safe
    static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    // assign to username for next connecting user
    static int nextUserNumber = 1;
    static Match match = null;
    static DatabaseMock mockDB = null;

    public static void main(String[] args) {
        AppMain.mockDB = new DatabaseMock();

        //AppMain.enableCORS("*","*","*");
        Gson gson = new Gson();
        Service http = Service.ignite();

        http.port(9999);

        // declaring websocket to watch the score
        http.webSocket("/score", ScoreWebSocketHandler.class);

        http.before((request, response) -> {
            response.type("application/json");
        });

        http.get("/test", (req, res) -> "plop42", gson::toJson);
        http.get("/test/:id", (req, res) -> Integer.valueOf(req.params("id")), gson::toJson);
        http.get("/test", (req, res) -> req.params("plopplop"));

        http.get("/players", (req, res) -> AppMain.mockDB.getPlayerList(), gson::toJson);


        AppMain.match = new Match(
                AppMain.mockDB.getPlayerList().get(0),
                AppMain.mockDB.getPlayerList().get(1)
        );

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if(!AppMain.match.hasEnded()) {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    AppMain.match.grantPointA();
                } else {
                    AppMain.match.grantPointB();
                }

                AppMain.broadcastScore(AppMain.match);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    // sends a message from one user to all users, along with a list of current usernames
    public static void broadcastScore(Match match) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(new Gson().toJson(match));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
