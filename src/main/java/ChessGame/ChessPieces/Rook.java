package ChessGame.ChessPieces;

import java.util.HashMap;

public class Rook implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♖";
    public static final String PRINT_CODE_BLACK = "♜";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public Rook(int playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Rook setPiece(int playerNumber) {
        return new Rook(playerNumber);
    }

    private void setPrintCode() {
        if (playerNumber == PLAYER_NUMBER_ONE) {
            printCode = PRINT_CODE_WHITE;
        } else {
            printCode = PRINT_CODE_BLACK;
        }
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public String getPrintCode() {
        return printCode;
    }

    @Override
    public boolean isMovable(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return fromPosition.getX() == toPosition.getX() || fromPosition.getY() == toPosition.getY();
    }
}
