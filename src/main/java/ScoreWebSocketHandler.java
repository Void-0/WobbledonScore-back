import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class ScoreWebSocketHandler {

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        String username = "User" + AppMain.nextUserNumber++;
        AppMain.userUsernameMap.put(user, username);
        AppMain.broadcastScore(AppMain.match);
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        AppMain.userUsernameMap.remove(user);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        AppMain.broadcastScore(AppMain.match); //if the user requests a manual refresh
    }

}