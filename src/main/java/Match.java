import java.util.Stack;

public class Match {
    private Player playerA;
    private Player playerB;
    private Score score;
    private Stack<Score> scoreLog;

    /**
     * Match constructor for matches that are yet to start.
     *
     * @param mPlayerA the player A.
     * @param mPlayerB the player B.
     */
    public Match(Player mPlayerA, Player mPlayerB) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.score = new Score();

        //starting the match with a score of 0 for both players
        this.scoreLog = new Stack<>();
        this.scoreLog.push(new Score());
    }

    /** Match constructor for making a custom match.
     *
     * @param mPlayerA the player A.
     * @param mPlayerB the player B.
     * @param mScore the custom score.
     * @param log the custom score log.
     */
    public Match(Player mPlayerA, Player mPlayerB, Score mScore, Stack<Score> log) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.score = mScore;
        this.scoreLog = log;
    }

    /**
     * Grants a point to player A.
     */
    public void grantPointA () {
        this.score.grantPointA();
        this.scoreLog.push(new Score(this.score));
    }

    /**
     * Grants a point to player B.
     */
    public void grantPointB () {
        this.score.grantPointB();
        this.scoreLog.push(new Score(this.score));
    }

    public boolean hasEnded() {
        return this.score.getHasEnded();
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Stack<Score> getScoreLog() {
        return scoreLog;
    }

    public void setScoreLog(Stack<Score> scoreLog) {
        this.scoreLog = scoreLog;
    }
}
