package ChessGame.ChessPieces;

import ChessGame.Exception.NotRightMoveException;

import java.util.HashMap;

public class Knight implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♘";
    public static final String PRINT_CODE_BLACK = "♞";
    int playerNumber;
    String printCode;

    public Knight(int playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Knight setPiece(int playerNumber) {
        return new Knight(playerNumber);
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
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        validateKnightMove(fromPosition, toPosition);
    }

    private void validateKnightMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (Math.pow((fromPosition.getX() - toPosition.getX()), 2) + Math.pow((fromPosition.getY() - toPosition.getY()), 2) != 5) {
            throw new NotRightMoveException();
        }
    }
}
