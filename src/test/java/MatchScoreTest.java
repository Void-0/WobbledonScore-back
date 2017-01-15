import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Match sore tests.
 */
public class MatchScoreTest {
    @Test
    public void should_grant_game_to_scorer_when_in_advantage() throws Exception {
        MatchScore score = new MatchScore(50, 40, 0, 0, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(0, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(1, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(0, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }
}