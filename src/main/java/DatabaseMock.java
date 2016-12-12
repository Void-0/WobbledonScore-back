import java.util.ArrayList;

public class DatabaseMock {
    private ArrayList<Player> playerList;
    private ArrayList<Match>  matchList;


    public DatabaseMock() {
        this.playerList = new ArrayList<>();
        this.playerList.add(new Player(
                "Luka Kosoric",
                27,
                0,
                "The game's architect."
        ));
        this.playerList.add(new Player(
                "Stephen Hawking",
                74,
                96,
                "Apparently jet propulsion wheelchairs and robotic tentacle arms are legal now."
        ));
        this.playerList.add(new Player(
                "Nikola Tesla",
                160,
                42,
                "A being of pure electric energy."
        ));
        this.playerList.add(new Player(
                "Novak Djokovic",
                29,
                1,
                "The only *normal* player around."
        ));
        this.playerList.add(new Player(
                "Alucard Relapmi",
                999,
                66,
                "Only plays at night."
        ));

        this.matchList = new ArrayList<>();
        this.matchList.add(
                new Match(
                        this.playerList.get(0),
                        this.playerList.get(2),
                        new Score(
                                0,
                                0,
                                0,
                                0,
                                3,
                                1,
                                true,
                                "A"
                        ),
                        null
                )
        );
        this.matchList.add(
                new Match(
                        this.playerList.get(1),
                        this.playerList.get(3),
                        new Score(
                                0,
                                0,
                                0,
                                0,
                                2,
                                3,
                                true,
                                "B"
                        ),
                        null
                )
        );
        this.matchList.add(
                new Match(
                        this.playerList.get(2),
                        this.playerList.get(4),
                        new Score(
                                0,
                                0,
                                0,
                                0,
                                3,
                                2,
                                true,
                                "A"
                        ),
                        null
                )
        );
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(ArrayList<Match> matchList) {
        this.matchList = matchList;
    }
}
