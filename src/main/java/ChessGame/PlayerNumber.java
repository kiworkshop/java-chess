package ChessGame;

public enum PlayerNumber {
    PLAYER_NUMBER_ONE(1, "백"),
    PLAYER_NUMBER_TWO(2, "흑");

    private int playerNumber;
    private String playerColorName;

    PlayerNumber(int playerNumber, String playerColorName) {
        this.playerNumber = playerNumber;
        this.playerColorName = playerColorName;
    }

    public String getPlayerColorName() {
        return playerColorName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public PlayerNumber next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
