package fnb.game.mancala.service;

import fnb.game.mancala.model.Game;
import fnb.game.mancala.model.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Service
public class GameService {
    private Game game;

    public GameService() {
        this.game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public void makeMove(int pitIndex) {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = (currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();

        int stonesToDistribute = currentPlayer.getStonesFromPit(pitIndex);
        int currentPitIndex = pitIndex;

        while (stonesToDistribute > 0) {
            currentPitIndex = (currentPitIndex + 1) % 14;

            // Skip opponent's large pit (for simplicity, assume it's at index 7 or 14)
            if (currentPitIndex != 7 && currentPitIndex != 14) {
                currentPlayer.addStoneToPit(currentPitIndex);
                stonesToDistribute--;
            }
        }

        if (currentPlayer.shouldCapture(currentPitIndex)) {
            int opponentPitIndex = 5 - currentPitIndex;
            int stonesToCapture = opponent.getPits()[opponentPitIndex] + 1;

            currentPlayer.captureStones(currentPitIndex, stonesToCapture);
            opponent.getPits()[opponentPitIndex] = 0;
        }


        if (game.isGameOver()) {
            game.setStatus("Game Over");
            Player winner = game.determineWinner();
            if (winner != null) {
                game.setWinner(winner == game.getPlayer1() ? "player1" : "player2");
            } else {
                game.setWinner("It's a tie");
            }
        } else {
            game.switchTurn();
        }

    }
}