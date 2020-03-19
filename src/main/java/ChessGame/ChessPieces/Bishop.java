package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;
import ChessGame.Exception.NotRightMoveException;
import ChessGame.PlayerNumber;

import java.util.HashMap;

import static ChessGame.PlayerNumber.PLAYER_NUMBER_ONE;

public class Bishop implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♗";
    public static final String PRINT_CODE_BLACK = "♝";

    PlayerNumber playerNumber;
    String printCode;

    public Bishop(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Bishop setPiece(PlayerNumber playerNumber) {
        return new Bishop(playerNumber);
    }

    @Override
    public PlayerNumber getPlayerNumber() {
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
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException, CannotJumptException {
        validateBishopMove(fromPosition, toPosition);
        ChessPieceMoveValidateService.validateDiagonalJumpMove(chessPieces, fromPosition, toPosition);
    }

    private void validateBishopMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (Math.abs(toPosition.getX() - fromPosition.getX()) != Math.abs(toPosition.getY() - fromPosition.getY())) {
            throw new NotRightMoveException();
        }
    }
}
