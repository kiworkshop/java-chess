package ChessGame.ChessPieces;

import ChessGame.Exception.NotRightMoveException;

import java.util.HashMap;

public class King implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♔";
    public static final String PRINT_CODE_BLACK = "♚";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public King(int playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static King setPiece(int playerNumber) {
        return new King(playerNumber);
    }

    private void setPrintCode() {
        if (playerNumber == 1) {
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
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        validateKingMove(fromPosition, toPosition);
    }

    private void validateKingMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (Math.sqrt((fromPosition.getX() - toPosition.getX()) ^ 2 + (fromPosition.getY() - toPosition.getY()) ^ 2) >= 2) {
            throw new NotRightMoveException();
        }
    }
}
