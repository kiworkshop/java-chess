package ChessPieces;

public class King implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♔";
    public static final String PRINT_CODE_BLACK = "♚";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public King(int playerNumber, ChessPiecePosition position) {
        this.playerNumber = playerNumber;
        this.position = position;
        setPrintCode();
    }

    public static King setPiece(int playerNumber, ChessPiecePosition position) {
        return new King(playerNumber, position);
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

    @Override
    public void move(ChessPiecePosition toPosition) {
        if (this.playerNumber == 1) {   // TODO Depth를 1로 만들 수 있으나 그러지 않는 것이 가독성이 좋음.
            if (position.getX() == toPosition.getX() && position.getY() + 1 == toPosition.getY()) {
                this.position = toPosition;
            }
        }
        if (this.playerNumber == 2) {
            if (position.getX() == toPosition.getX() && position.getY() - 1 == toPosition.getY()) {
                this.position = toPosition;
            }
        }
    }
}
