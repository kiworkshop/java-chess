package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;
import ChessGame.Exception.NotRightMoveException;

import java.util.HashMap;

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
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws Exception {
        validateQueenMove(fromPosition, toPosition);
        ChessPieceMoveValidateService.validateVerticalJumpMove(chessPieces, fromPosition, toPosition);
        ChessPieceMoveValidateService.validateHorizontalJumpMove(chessPieces, fromPosition, toPosition);
        ChessPieceMoveValidateService.validateDiagonalJumpMove(chessPieces, fromPosition, toPosition);
    }

    private void validateQueenMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (!(fromPosition.getX() == toPosition.getX() || fromPosition.getY() == toPosition.getY() || Math.abs(toPosition.getX() - fromPosition.getX()) == Math.abs(toPosition.getY() - fromPosition.getY()))) {
            throw new NotRightMoveException();
        }
    }


}
