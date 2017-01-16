public class MatchScore {
    private PlayerScore scorePlayerA;
    private PlayerScore scorePlayerB;
    private boolean hasEnded;
    private Scorer lastPoint;

    /**
     *  MatchScore constructor used when initialising a new game where no one has scored yet.
     */
    public MatchScore() {
        scorePlayerA = new PlayerScore();
        scorePlayerB = new PlayerScore();

        hasEnded = false;
        lastPoint = Scorer.NONE;
    }

    /**
     * MatchScore constructor used when we need to set a score manually.
     *
     * @param pointsPlayerA number of points player A has.
     * @param pointsPlayerB number of points player B has.
     * @param gamesPlayerA number of games player A has.
     * @param gamesPlayerB number of games player B has.
     * @param setsPlayerA number of sets player A has.
     * @param setsPlayerB number of sets player B has.
     * @param end indicating if the match has ended.
     * @param win contains Scorer.PLAYER_A or Scorer.PLAYER_B, indicating who the winner is.
     */
    public MatchScore(int pointsPlayerA, int pointsPlayerB, int gamesPlayerA, int gamesPlayerB, int setsPlayerA, int setsPlayerB, boolean end, Scorer win) {
        scorePlayerA = new PlayerScore(pointsPlayerA, gamesPlayerA, setsPlayerA);
        scorePlayerB = new PlayerScore(pointsPlayerB, gamesPlayerB, setsPlayerB);

        hasEnded = end;
        lastPoint = win;
    }

    /**
     * MatchScore constructor used if we want to copy an existing score.
     *
     * @param newMatchScore the score we want to copy.
     */
    public MatchScore(MatchScore newMatchScore) {
        scorePlayerA = new PlayerScore(newMatchScore.getScorePlayerA().getPoints(), newMatchScore.getScorePlayerA().getGames(), newMatchScore.getScorePlayerA().getSets());
        scorePlayerB = new PlayerScore(newMatchScore.getScorePlayerB().getPoints(), newMatchScore.getScorePlayerB().getGames(), newMatchScore.getScorePlayerB().getSets());

        hasEnded = newMatchScore.getHasEnded();
        lastPoint = newMatchScore.getLastPoint();
    }

    /**
     * Grants a point to a given player.
     * @param pointWinner the winner of the point.
     * @param opponent the other player.
     */
    public void grantPoint(PlayerScore pointWinner, PlayerScore opponent) {
        if(pointWinner.getGames() == 6 && opponent.getGames() == 6) {
            // a player wins the tie break if he reaches a score of 7 or greater with a 2 points difference
            if(pointWinner.getPoints() > 5 && pointWinner.getPoints() - opponent.getPoints() > 0) {
                this.grantGame(pointWinner, opponent);
            } else {
                pointWinner.setPoints(pointWinner.getPoints() + 1);
            }
        } else if(pointWinner.getPoints() < 40) {
            // we can either get a 15, 30 or 40 score
            pointWinner.setPoints(Math.min(pointWinner.getPoints() + 15, 40));
        } else if(pointWinner.getPoints() == 40 && opponent.getPoints() > 30) {
            // we're in a deuce
            if(opponent.getPoints() == 40) {
                // its deuce so A gets advantage
                pointWinner.setPoints(50);
            } else {
                // B = 50 here so back to deuce
                opponent.setPoints(40);
            }
        } else {
            // here either its A = 40 while B < 30 or A = 50 and B = 40 meaning A wins the game
            this.grantGame(pointWinner, opponent);
        }
    }

    /**
     * Grants a point to player A.
     */
    public void grantPointPlayerA() {
        this.lastPoint = Scorer.PLAYER_A;
        grantPoint(scorePlayerA, scorePlayerB);
    }

    /**
     * Grants a point to player B.
     */
    public void grantPointPlayerB() {
        this.lastPoint = Scorer.PLAYER_B;
        grantPoint(scorePlayerB, scorePlayerA);
    }

    /**
     * Grants a Game to a given player.
     * @param pointWinner
     * @param opponent
     */
    public void grantGame(PlayerScore pointWinner, PlayerScore opponent) {
        pointWinner.setPoints(0);
        opponent.setPoints(0);

        // tie break is handled by the points function
        if(pointWinner.getGames() > 4 && (pointWinner.getGames() - opponent.getGames() > 0 || pointWinner.getGames() == 6 && opponent.getGames() == 6)) {
            // A gets a set if A = 6 when B < 5 or if A = 7 when B = 5 or if A = 7 when B = 6
            this.grantSet(pointWinner, opponent);
        } else {
            pointWinner.setGames(pointWinner.getGames() + 1);
        }
    }

    /**
     * Grants a set to a given player.
     * @param pointWinner
     * @param opponent
     */
    private void grantSet(PlayerScore pointWinner, PlayerScore opponent) {
        pointWinner.setGames(0);
        opponent.setGames(0);

        // best of 5 sets victory condition
        if(pointWinner.getSets() > 1) {
            hasEnded = true;
        }

        pointWinner.setSets(pointWinner.getSets() + 1);
    }

    public PlayerScore getScorePlayerA() {
        return scorePlayerA;
    }

    public void setScorePlayerA(PlayerScore scorePlayerA) {
        this.scorePlayerA = scorePlayerA;
    }

    public PlayerScore getScorePlayerB() {
        return scorePlayerB;
    }

    public void setScorePlayerB(PlayerScore scorePlayerB) {
        this.scorePlayerB = scorePlayerB;
    }

    public boolean getHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public Scorer getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Scorer lastPoint) {
        this.lastPoint = lastPoint;
    }
}
