package fnb.game.mancala.model;

public class GameState {
    private int[] playerOnePits;
    private int[] playerTwoPits;
    private int playerOneStore;
    private int playerTwoStore;

    // getters and setters


    public int[] getPlayerOnePits() {
        return playerOnePits;
    }

    public void setPlayerOnePits(int[] playerOnePits) {
        this.playerOnePits = playerOnePits;
    }

    public int[] getPlayerTwoPits() {
        return playerTwoPits;
    }

    public void setPlayerTwoPits(int[] playerTwoPits) {
        this.playerTwoPits = playerTwoPits;
    }

    public int getPlayerOneStore() {
        return playerOneStore;
    }

    public void setPlayerOneStore(int playerOneStore) {
        this.playerOneStore = playerOneStore;
    }

    public int getPlayerTwoStore() {
        return playerTwoStore;
    }

    public void setPlayerTwoStore(int playerTwoStore) {
        this.playerTwoStore = playerTwoStore;
    }
}
