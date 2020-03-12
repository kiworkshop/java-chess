package ChessPieces;

public class Queen implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♕";
    public static final String PRINT_CODE_BLACK = "♛";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public Queen(int playerNumber, ChessPiecePosition position) {
        this.playerNumber = playerNumber;
        this.position = position;
        setPrintCode();
    }

    public static Queen setPiece(int playerNumber, ChessPiecePosition position) {
        return new Queen(playerNumber, position);
    }

    private void setPrintCode() {
        if (playerNumber == 1) {
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
    public ChessPiecePosition getPosition() {
        return position;
    }
}
