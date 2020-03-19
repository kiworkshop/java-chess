package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;
import ChessGame.Exception.NotRightMoveException;
import ChessGame.PlayerNumber;

import java.util.HashMap;

import static ChessGame.PlayerNumber.PLAYER_NUMBER_ONE;

public class Rook implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♖";
    public static final String PRINT_CODE_BLACK = "♜";
    PlayerNumber playerNumber;
    String printCode;
    ChessPiecePosition position;

    public Rook(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Rook setPiece(PlayerNumber playerNumber) {
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
    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public String getPrintCode() {
        return printCode;
    }

    @Override
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException, CannotJumptException {
        validateRookMove(fromPosition, toPosition);
        ChessPieceMoveValidateService.validateVerticalJumpMove(chessPieces, fromPosition, toPosition);
        ChessPieceMoveValidateService.validateHorizontalJumpMove(chessPieces, fromPosition, toPosition);
    }

    private void validateRookMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (!(fromPosition.getX() == toPosition.getX() || fromPosition.getY() == toPosition.getY())) {
            throw new NotRightMoveException();
        }
    }
}
