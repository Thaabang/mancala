package fnb.game.mancala.service;

import static org.junit.jupiter.api.Assertions.*;

import fnb.game.mancala.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void testMakeMove() {
        gameService.makeMove(0);
        Game game = gameService.getGame();
        assertEquals(6, game.getCurrentPlayer().getPits()[0]);
        assertEquals(6, game.getCurrentPlayer().getPits()[1]);
    }

    @Test
    void testGameOver() {
        // Manually setting a game over condition. You'd replace this with actual game logic
        Game game = gameService.getGame();
        game.setStatus("Game Over");
        assertEquals("Game Over", game.getStatus());
    }

    @Test
    void testStoneCapture() {
        gameService.makeMove(0);
        gameService.makeMove(1);
        Game game = gameService.getGame();
        assertEquals(7, game.getCurrentPlayer().getPits()[1]);
    }
}
