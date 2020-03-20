package mychess.domain;

public class Players {

    private Player currentPlayer;
    private Player nextPlayer;

    public Players(Player playerWhite, Player playerBlack) {
        this.currentPlayer = playerWhite;
        this.nextPlayer = playerBlack;
    }

    public void changeCurrentPlayer() {
        Player tempPlayer = currentPlayer;
        this.currentPlayer = nextPlayer;
        this.nextPlayer = tempPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
