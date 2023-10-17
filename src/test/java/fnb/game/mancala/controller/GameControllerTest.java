package fnb.game.mancala.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import fnb.game.mancala.model.Game;
import fnb.game.mancala.model.Move;
import fnb.game.mancala.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void makeMove() throws Exception {
        Move move = new Move();
        Game game = new Game();

        doNothing().when(gameService).makeMove(anyInt());
        when(gameService.getGame()).thenReturn(game);

        mockMvc.perform(post("/api/game/makeMove")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(move)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        verify(gameService).makeMove(0);
    }

    @Test
    void getGameState() throws Exception {
        Game game = new Game();  // prepare expected game object
        when(gameService.getGame()).thenReturn(game);

        mockMvc.perform(get("/api/game/state"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());  // validate response if needed
    }

    @Test
    void makeMoveWithPathVar() throws Exception {
        Game game = new Game();  // prepare expected game object

        doNothing().when(gameService).makeMove(anyInt());
        when(gameService.getGame()).thenReturn(game);

        mockMvc.perform(post("/api/game/move/3"))
                .andExpect(status().isOk());

        verify(gameService).makeMove(3);
        verify(messagingTemplate).convertAndSend(eq("/topic/gameState"), eq(game));
    }

}
