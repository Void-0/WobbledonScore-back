import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Match score tests. Covering major score changes related to tennis rules such as deuce and tie break.
 */
public class MatchScoreTest {
    @Test
    public void should_win_game_if_player_scores_a_point_after_scoring_40() throws Exception {
        MatchScore score = new MatchScore(40, 0, 0, 0, 0, 0, false, Scorer.PLAYER_A);
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

    @Test
    public void should_get_advantage_if_player_scores_during_deuce() throws Exception {
        MatchScore score = new MatchScore(40, 40, 0, 0, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(50, score.getScorePlayerA().getPoints());
        assertEquals(40, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(0, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

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

    @Test
    public void should_go_back_to_deuce_if_player_scores_during_opponents_advantage() throws Exception {
        MatchScore score = new MatchScore(50, 40, 0, 0, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerB();

        assertEquals(Scorer.PLAYER_B, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(40, score.getScorePlayerA().getPoints());
        assertEquals(40, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(0, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

    @Test
    public void should_win_set_if_player_scores_sixth_game() throws Exception {
        MatchScore score = new MatchScore(40, 30, 5, 3, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(0, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(1, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

    @Test
    public void should_win_set_if_player_gets_two_points_advantage_while_opponent_has_five() throws Exception {
        MatchScore score = new MatchScore(40, 30, 6, 5, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(0, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(1, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

    @Test
    public void should_enter_tie_break_if_both_players_scored_six_sets() throws Exception {
        MatchScore score = new MatchScore(0, 0, 6, 6, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(1, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(6, score.getScorePlayerA().getGames());
        assertEquals(6, score.getScorePlayerB().getGames());
        assertEquals(0, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

    @Test
    public void should_win_tie_break_if_player_scored_seventh_point() throws Exception {
        MatchScore score = new MatchScore(6, 4, 6, 6, 0, 0, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertFalse(score.getHasEnded());
        assertEquals(0, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(1, score.getScorePlayerA().getSets());
        assertEquals(0, score.getScorePlayerB().getSets());
    }

    @Test
    public void should_win_match_if_player_wins_three_sets() throws Exception {
        MatchScore score = new MatchScore(40, 15, 5, 3, 2, 1, false, Scorer.PLAYER_A);
        score.grantPointPlayerA();

        assertEquals(Scorer.PLAYER_A, score.getLastPoint());
        assertTrue(score.getHasEnded());
        assertEquals(0, score.getScorePlayerA().getPoints());
        assertEquals(0, score.getScorePlayerB().getPoints());
        assertEquals(0, score.getScorePlayerA().getGames());
        assertEquals(0, score.getScorePlayerB().getGames());
        assertEquals(3, score.getScorePlayerA().getSets());
        assertEquals(1, score.getScorePlayerB().getSets());
    }
}