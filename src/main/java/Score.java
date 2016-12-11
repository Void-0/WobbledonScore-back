import java.util.ArrayList;

public class Score {
    private int pointsA;
    private int pointsB;
    private int gameA;
    private int gameB;
    private int setA;
    private int setB;
    private boolean hasEnded;

    // called when initialising a new game where no one has scored yet
    public Score () {
        this.pointsA = 0;
        this.pointsB = 0;
        this.gameA = 0;
        this.gameB = 0;
        this.setA = 0;
        this.setB = 0;

        this.hasEnded = false;
    }

    // called when we need to set a score manually, of a past match for example
    public Score (int pointsA, int pointsB, int gameA, int gameB, int setA, int setB, boolean end) {
        this.pointsA = pointsA;
        this.pointsB = pointsB;
        this.gameA = gameA;
        this.gameB = gameB;
        this.setA = setA;
        this.setB = setB;

        this.hasEnded = end;
    }

    public Score (Score newScore) {
        this.pointsA = newScore.getPointsA();
        this.pointsB = newScore.getPointsB();
        this.gameA = newScore.getGameA();
        this.gameB = newScore.getGameB();
        this.setA = newScore.getSetA();
        this.setB = newScore.getSetB();

        this.hasEnded = newScore.getHasEnded();
    }

    public void grantPointA() {
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

    public void grantPointB() {
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

    private void grantSetB() {
        this.gameA = 0;
        this.gameB = 0;

        // best of 5 sets victory condition
        if(this.setB > 2) {
            this.hasEnded = true;
        } else {
            this.setB++;
        }
    }

    private void grantSetA() {
        this.gameA = 0;
        this.gameB = 0;

        // best of 5 sets victory condition
        if(this.setA > 2) {
            this.hasEnded = true;
        } else {
            this.setA++;
        }
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
}
