package fnb.game.mancala.model;

public class Player {
    private String name;
    private int[] pits;
    private int largePit;

    public Player() {
        // Initialize pits with 6 stones each and large pit with 0 stones
        this.pits = new int[]{6, 6, 6, 6, 6, 6};
        this.largePit = 0;
    }

    // Getters and setters

    public int[] getPits() {
        return pits;
    }

    public void setPits(int[] pits) {
        this.pits = pits;
    }

    public int getLargePit() {
        return largePit;
    }

    public void setLargePit(int largePit) {
        this.largePit = largePit;
    }

    public int getStonesFromPit(int pitIndex) {
        if (pitIndex < 0 || pitIndex >= pits.length) {
            throw new ArrayIndexOutOfBoundsException("Pit index out of bounds");
        }

        int stones = pits[pitIndex];
        pits[pitIndex] = 0;
        return stones;
    }


    public void addStoneToPit(int pitIndex) {
        if (pitIndex >= 0 && pitIndex < pits.length) {
            pits[pitIndex]++;
        } else {
            System.out.println("Invalid pit index: " + pitIndex);
        }
    }


    public void addStoneToLargePit() {
        largePit++;
    }

    public boolean ownsPit(int pitIndex) {
        return pitIndex >= 0 && pitIndex < pits.length;
    }

    public void captureStones(int pitIndex, int stonesToCapture) {
        largePit += stonesToCapture;
        pits[pitIndex] = 0; // Clear the pit
    }

    public boolean shouldCapture(int pitIndex) {
        return ownsPit(pitIndex) && pits[pitIndex] == 1;
    }

    public String getName() {
        return this.name;
    }
}


