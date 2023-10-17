package fnb.game.mancala.model;

import java.util.Arrays;

public class Game {

    private String name;
    private Player currentPlayer;
    private String status;
    private Player player1;
    private Player player2;
    private String winner;

    public Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.currentPlayer = player1;
        this.status = "ongoing";
        this.winner = null;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean isGameOver() {
        return Arrays.stream(player1.getPits()).sum() == 0 || Arrays.stream(player2.getPits()).sum() == 0;
    }

    public Player determineWinner() {
        if (player1.getLargePit() > player2.getLargePit()) {
            return player1;
        } else if (player2.getLargePit() > player1.getLargePit()) {
            return player2;
        } else {
            return null; // It's a tie
        }
    }

    public void updateGameStatus() {
        if (isGameOver()) {
            this.status = "Game Over";
            this.winner = String.valueOf(determineWinner());
        }
    }

    public void switchTurn() {
        switchCurrentPlayer();
    }
}
