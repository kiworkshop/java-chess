package ChessPieces;

public class Queen implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♕";
    public static final String PRINT_CODE_BLACK = "♛";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public Queen(int playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Queen setPiece(int playerNumber) {
        return new Queen(playerNumber);
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
        if (this.playerNumber == PLAYER_NUMBER_ONE) {   // TODO Depth를 1로 만들 수 있으나 그러지 않는 것이 가독성이 좋음.
            if (fromPosition.getX() == toPosition.getX() && fromPosition.getY() + 1 == toPosition.getY()) {
                return true;
            }
        }
        if (this.playerNumber == PLAYER_NUMBER_TWO) {
            return fromPosition.getX() == toPosition.getX() && fromPosition.getY() + 1 == toPosition.getY();
        }
        return false;
    }
}
