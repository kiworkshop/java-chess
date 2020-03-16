package ChessGame;

public enum PlayerNumber {
    PLAYER_NUMBER_ONE(1),
    PLAYER_NUMBER_TWO(2);

    private int playerNumber;

    PlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public PlayerNumber next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
