package fnb.game.mancala.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

        private Game game;

        @BeforeEach
        void setUp() {
            game = new Game();
        }

        @Test
        void switchCurrentPlayer() {
            Player player1 = game.getPlayer1();
            Player player2 = game.getPlayer2();

            assertEquals(player1, game.getCurrentPlayer());

            game.switchCurrentPlayer();
            assertEquals(player2, game.getCurrentPlayer());

            game.switchCurrentPlayer();
            assertEquals(player1, game.getCurrentPlayer());
        }

        @Test
        void isGameOver() {
            // Initialize game state such that it's not over
            assertFalse(game.isGameOver());

            // Simulate game over state for player1
            game.getPlayer1().setPits(new int[] {0, 0, 0, 0, 0, 0});
            assertTrue(game.isGameOver());

            // Reset
            game.getPlayer1().setPits(new int[] {4, 4, 4, 4, 4, 4});
            assertFalse(game.isGameOver());

            // Simulate game over state for player2
            game.getPlayer2().setPits(new int[] {0, 0, 0, 0, 0, 0});
            assertTrue(game.isGameOver());
        }

        @Test
        void determineWinner() {
            game.getPlayer1().setLargePit(25);
            game.getPlayer2().setLargePit(20);

            assertEquals(game.getPlayer1(), game.determineWinner());

            game.getPlayer2().setLargePit(30);
            assertEquals(game.getPlayer2(), game.determineWinner());

            game.getPlayer2().setLargePit(25);
            assertNull(game.determineWinner()); // It's a tie
        }

        @Test
        void updateGameStatus() {
            assertEquals("ongoing", game.getStatus());
            assertNull(game.getWinner());

            // Simulate game over
            game.getPlayer1().setPits(new int[] {0, 0, 0, 0, 0, 0});
            game.getPlayer1().setLargePit(30);
            game.updateGameStatus();

            assertEquals("Game Over", game.getStatus());
        }

        @Test
        void switchTurn() {
            Player player1 = game.getPlayer1();
            Player player2 = game.getPlayer2();

            assertEquals(player1, game.getCurrentPlayer());

            game.switchTurn();
            assertEquals(player2, game.getCurrentPlayer());

            game.switchTurn();
            assertEquals(player1, game.getCurrentPlayer());
        }
    }
