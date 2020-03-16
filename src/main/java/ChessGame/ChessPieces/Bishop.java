package ChessGame.ChessPieces;

public class Bishop implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♗";
    public static final String PRINT_CODE_BLACK = "♝";

    int playerNumber;
    String printCode;

    public Bishop(int playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Bishop setPiece(int playerNumber) {
        return new Bishop(playerNumber);
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    private void setPrintCode() {
        if (playerNumber == PLAYER_NUMBER_ONE) {
            printCode = PRINT_CODE_WHITE;
        } else {
            printCode = PRINT_CODE_BLACK;
        }
    }

    @Override
    public String getPrintCode() {
        return printCode;
    }

    @Override
    public boolean isMovable(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return Math.abs(toPosition.getX() - fromPosition.getX()) == Math.abs(toPosition.getY() - fromPosition.getY());
    }
}
