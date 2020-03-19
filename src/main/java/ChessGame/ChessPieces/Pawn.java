package ChessGame.ChessPieces;


import ChessGame.Exception.NotRightMoveException;
import ChessGame.PlayerNumber;

import java.util.HashMap;

import static ChessGame.PlayerNumber.PLAYER_NUMBER_ONE;
import static ChessGame.PlayerNumber.PLAYER_NUMBER_TWO;

public class Pawn implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♙";
    public static final String PRINT_CODE_BLACK = "♟";
    PlayerNumber playerNumber;
    String printCode;

    public Pawn(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        setPrintCode();
    }

    public static Pawn setPiece(PlayerNumber playerNumber) {
        return new Pawn(playerNumber);
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
    public void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws NotRightMoveException {
        if (this.playerNumber == PLAYER_NUMBER_ONE) {
            if (fromPosition.getX() == toPosition.getX() && fromPosition.getY() + 1 != toPosition.getY()) {
                throw new NotRightMoveException();
            }
        }
        if (this.playerNumber == PLAYER_NUMBER_TWO) {
            if (fromPosition.getX() == toPosition.getX() && fromPosition.getY() - 1 != toPosition.getY()) {
                throw new NotRightMoveException();
            }
        }
    }
}

