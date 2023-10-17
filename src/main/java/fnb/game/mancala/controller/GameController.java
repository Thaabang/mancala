package fnb.game.mancala.controller;


import fnb.game.mancala.model.Game;
import fnb.game.mancala.model.Move;
import fnb.game.mancala.service.GameService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/makeMove")
    public Game makeMove(@RequestBody Move move) {
        gameService.makeMove(move.getPitIndex());
        return gameService.getGame();
    }

    @GetMapping("/state")
    public Game getGameState() {
        return gameService.getGame();
    }

    @PostMapping("/move/{pitIndex}")
    public void makeMoveWithPathVar(@PathVariable int pitIndex) {
        gameService.makeMove(pitIndex);
        messagingTemplate.convertAndSend("/topic/gameState", gameService.getGame());
    }
}

