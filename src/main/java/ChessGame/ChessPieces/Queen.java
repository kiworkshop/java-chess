package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;

import java.util.HashMap;
import java.util.stream.Collectors;

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
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public String getPrintCode() {
        return printCode;
    }

    @Override
    public boolean isMovable(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException {
        if (fromPosition.getX() == toPosition.getX()) {    //종이동
            if (chessPieces.entrySet().stream().anyMatch(e -> e.getKey().getX() == fromPosition.getX() && isBetween(fromPosition.getY(), toPosition.getY(), e.getKey().getY()))) {
                throw new CannotJumptException();
            }
        }
        return fromPosition.getX() == toPosition.getX() || fromPosition.getY() == toPosition.getY() || Math.abs(toPosition.getX() - fromPosition.getX()) == Math.abs(toPosition.getY() - fromPosition.getY());
    }

    public static boolean isBetween(int a, int b, int c) {
        return b > a ? c > a && c < b : c > b && c < a;
    }
}
