import java.util.Stack;

public class Match {
    private Player playerA;
    private Player playerB;
    private MatchScore matchScore;
    private Stack<MatchScore> matchScoreLog;

    /**
     * Match constructor for matches that are yet to start.
     *
     * @param mPlayerA the player A.
     * @param mPlayerB the player B.
     */
    public Match(Player mPlayerA, Player mPlayerB) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.matchScore = new MatchScore();

        //starting the match with a matchScore of 0 for both players
        this.matchScoreLog = new Stack<>();
        this.matchScoreLog.push(new MatchScore());
    }

    /** Match constructor for making a custom match.
     *
     * @param mPlayerA the player A.
     * @param mPlayerB the player B.
     * @param mMatchScore the custom matchScore.
     * @param log the custom matchScore log.
     */
    public Match(Player mPlayerA, Player mPlayerB, MatchScore mMatchScore, Stack<MatchScore> log) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.matchScore = mMatchScore;
        this.matchScoreLog = log;
    }

    /**
     * Grants a point to player A.
     */
    public void grantPointA () {
        this.matchScore.grantPointPlayerA();
        this.matchScoreLog.push(new MatchScore(this.matchScore));
    }

    /**
     * Grants a point to player B.
     */
    public void grantPointB () {
        this.matchScore.grantPointPlayerB();
        this.matchScoreLog.push(new MatchScore(this.matchScore));
    }

    public boolean hasEnded() {
        return this.matchScore.getHasEnded();
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

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public Stack<MatchScore> getMatchScoreLog() {
        return matchScoreLog;
    }

    public void setMatchScoreLog(Stack<MatchScore> matchScoreLog) {
        this.matchScoreLog = matchScoreLog;
    }
}
