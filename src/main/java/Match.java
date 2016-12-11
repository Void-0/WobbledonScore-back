import java.util.Stack;

public class Match {
    private Player playerA;
    private Player playerB;
    private Score score;
    private Stack<Score> scoreLog;

    // used when making a new match
    public Match(Player mPlayerA, Player mPlayerB) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.score = new Score();

        //starting the match with a score of 0 for both players
        this.scoreLog = new Stack<>();
        this.scoreLog.push(new Score());
    }

    // used when making a custom match, a past match for example
    public Match(Player mPlayerA, Player mPlayerB, Score mScore, Stack<Score> log) {
        this.playerA = mPlayerA;
        this.playerB = mPlayerB;
        this.score = mScore;
        this.scoreLog = log;
    }

    public void grantPointA () {
        this.score.grantPointA();
        this.scoreLog.push(new Score(this.score));
    }

    public void grantPointB () {
        this.score.grantPointB();
        this.scoreLog.push(new Score(this.score));
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
