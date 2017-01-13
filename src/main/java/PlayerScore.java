/**
 * Container class for a players individual score during a match.
 */
public class PlayerScore {
    private int points;
    private int games;
    private int sets;

    public PlayerScore() {
        points = 0;
        games = 0;
        sets = 0;
    }

    public PlayerScore(int points, int games, int sets) {
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
