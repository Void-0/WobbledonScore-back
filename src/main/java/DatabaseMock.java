import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mock Database class used instead of a real SQL or NoSQL database.
 */
public class DatabaseMock {
    private List<Player> playerList;
    private List<Match> matchList;

    /**
     * Constructor for the mock database. Loads some test data that will be used instead of DB calls.
     */
    public DatabaseMock() {
        this.playerList = Arrays.asList(
                new Player(
                        "Luka Kosoric",
                        27,
                        0,
                        "The game's architect."
                ), new Player(
                        "Stephen Hawking",
                        74,
                        96,
                        "Apparently jet propulsion wheelchairs and robotic tentacle arms are legal now."
                ), new Player(
                        "Nikola Tesla",
                        160,
                        42,
                        "A being of pure electric energy."
                ), new Player(
                        "Novak Djokovic",
                        29,
                        1,
                        "The only *normal* player around."
                ), new Player(
                        "Alucard Relapmi",
                        999,
                        66,
                        "Only plays at night."
                ));

        this.matchList = Arrays.asList(
                new Match(
                        this.playerList.get(0),
                        this.playerList.get(2),
                        new MatchScore(
                                0,
                                0,
                                0,
                                0,
                                3,
                                1,
                                true,
                                Scorer.PLAYER_A
                        ),
                        null
                ), new Match(
                        this.playerList.get(1),
                        this.playerList.get(3),
                        new MatchScore(
                                0,
                                0,
                                0,
                                0,
                                2,
                                3,
                                true,
                                Scorer.PLAYER_B
                        ),
                        null
                ), new Match(
                        this.playerList.get(2),
                        this.playerList.get(4),
                        new MatchScore(
                                0,
                                0,
                                0,
                                0,
                                3,
                                2,
                                true,
                                Scorer.PLAYER_A
                        ),
                        null
                )
        );
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(ArrayList<Match> matchList) {
        this.matchList = matchList;
    }
}
