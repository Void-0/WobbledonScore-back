public class MatchScore {
    private int pointsA;
    private int pointsB;
    private int gameA;
    private int gameB;
    private int setA;
    private int setB;
    private boolean hasEnded;
    private Scorer lastPoint;
    private Scorer winner;

    /**
     *  MatchScore constructor used when initialising a new game where no one has scored yet.
     */
    public MatchScore() {
        this.pointsA = 0;
        this.pointsB = 0;
        this.gameA = 0;
        this.gameB = 0;
        this.setA = 0;
        this.setB = 0;

        this.hasEnded = false;
        this.winner = Scorer.NONE;
        this.lastPoint = Scorer.NONE;
    }

    /**
     * MatchScore constructor used when we need to set a score manually.
     *
     * @param pointsA number of points player A has.
     * @param pointsB number of points player B has.
     * @param gameA number of games player A has.
     * @param gameB number of games player B has.
     * @param setA number of sets player A has.
     * @param setB number of sets player B has.
     * @param end indicating if the match has ended.
     * @param win contains Scorer.PLAYER_A or Scorer.PLAYER_B, indicating who the winner is.
     */
    public MatchScore(int pointsA, int pointsB, int gameA, int gameB, int setA, int setB, boolean end, Scorer win) {
        this.pointsA = pointsA;
        this.pointsB = pointsB;
        this.gameA = gameA;
        this.gameB = gameB;
        this.setA = setA;
        this.setB = setB;

        this.hasEnded = end;
        this.winner = win;
        this.lastPoint = win;
    }

    /**
     * MatchScore constructor used if we want to copy an existing score.
     *
     * @param newMatchScore the score we want to copy.
     */
    public MatchScore(MatchScore newMatchScore) {
        this.pointsA = newMatchScore.getPointsA();
        this.pointsB = newMatchScore.getPointsB();
        this.gameA = newMatchScore.getGameA();
        this.gameB = newMatchScore.getGameB();
        this.setA = newMatchScore.getSetA();
        this.setB = newMatchScore.getSetB();

        this.hasEnded = newMatchScore.getHasEnded();
        this.winner = newMatchScore.getWinner();
        this.lastPoint = newMatchScore.getLastPoint();
    }

    /**
     * Grants a point to player A.
     */
    public void grantPointA() {
        this.lastPoint = Scorer.PLAYER_A;
        if(this.gameA == 6 && this.gameB == 6) {
            // a player wins the tie break if he reaches a score of 7 or greater with a 2 points difference
            if(this.pointsA > 5 && this.pointsA - this.pointsB > 0) {
                this.grantGameA();
            } else {
                this.pointsA++;
            }
        } else if(this.pointsA < 40) {
            // we can either get a 15, 30 or 40 score
            this.pointsA = Math.min(this.pointsA + 15, 40);
        } else if(this.pointsA == 40 && this.pointsB > 30) {
            // we're in a deuce
            if(this.pointsB == 40) {
                // its deuce so A gets advantage
                this.pointsA = 50;
            } else {
                // B = 50 here so back to deuce
                this.pointsB = 40;
            }
        } else {
            // here either its A = 40 while B < 30 or A = 50 and B = 40 meaning A wins the game
            this.grantGameA();
        }
    }

    /**
     * Grants a point to player B.
     */
    public void grantPointB() {
        this.lastPoint = Scorer.PLAYER_B;
        if(this.gameA == 6 && this.gameB == 6) {
            // a player wins the tie break if he reaches a score of 7 or greater with a 2 points difference
            if(this.pointsB > 5 && this.pointsB - this.pointsA > 0) {
                this.grantGameB();
            } else {
                this.pointsB++;
            }
        } else if(this.pointsB < 40) {
            // we can either get a 15, 30 or 40 score
            this.pointsB = Math.min(this.pointsB + 15, 40);
        } else if(this.pointsB == 40 && this.pointsA > 30) {
            // we're in a deuce
            if(this.pointsA == 40) {
                // its deuce so B gets advantage
                this.pointsB = 50;
            } else {
                // A = 50 here so back to deuce
                this.pointsA = 40;
            }
        } else {
            // here either its B = 40 while A < 30 or B = 50 and A = 40 meaning B wins the game
            this.grantGameB();
        }
    }

    /**
     * Grants a Game to player A.
     */
    public void grantGameA() {
        this.pointsA = 0;
        this.pointsB = 0;

        // tie break is handled by the points function
        if(this.gameA > 4 && (this.gameA - this.gameB > 0)) {
            // A gets a set if A = 6 when B < 5 or if A = 7 when B = 5
            this.grantSetA();
        } else {
            this.gameA++;
        }
    }

    /**
     * Grants a Game to player B.
     */
    public void grantGameB() {
        this.pointsA = 0;
        this.pointsB = 0;

        // tie break is handled by the points function
        if(this.gameB > 4 && (this.gameB - this.gameA > 0)) {
            // B gets a set if B = 6 when A < 5 or if B = 7 when A = 5
            this.grantSetB();
        } else {
            this.gameB++;
        }
    }

    /**
     * Grants a set to player B.
     */
    private void grantSetB() {
        this.gameA = 0;
        this.gameB = 0;

        // best of 5 sets victory condition
        if(this.setB > 1) {
            this.hasEnded = true;
            this.winner = Scorer.PLAYER_B;
        }

        this.setB++;
    }

    /**
     * Grants a set to player A.
     */
    private void grantSetA() {
        this.gameA = 0;
        this.gameB = 0;

        // best of 5 sets victory condition
        if(this.setA > 1) {
            this.hasEnded = true;
            this.winner = Scorer.PLAYER_A;
        }

        this.setA++;
    }

    public int getPointsA() {
        return pointsA;
    }

    public void setPointsA(int pointsA) {
        this.pointsA = pointsA;
    }

    public int getPointsB() {
        return pointsB;
    }

    public void setPointsB(int pointsB) {
        this.pointsB = pointsB;
    }

    public int getGameA() {
        return gameA;
    }

    public void setGameA(int gameA) {
        this.gameA = gameA;
    }

    public int getGameB() {
        return gameB;
    }

    public void setGameB(int gameB) {
        this.gameB = gameB;
    }

    public int getSetA() {
        return setA;
    }

    public void setSetA(int setA) {
        this.setA = setA;
    }

    public int getSetB() {
        return setB;
    }

    public void setSetB(int setB) {
        this.setB = setB;
    }

    public boolean getHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public Scorer getWinner() {
        return winner;
    }

    public void setWinner(Scorer winner) {
        this.winner = winner;
    }

    public Scorer getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Scorer lastPoint) {
        this.lastPoint = lastPoint;
    }
}
