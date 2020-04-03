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
            if (!(validatePlayerOneBasicPawnMove(chessPieces, fromPosition, toPosition) || validatePlayerOneCaptureMove(chessPieces, fromPosition, toPosition) || validatePlayerOneFirstPawnMove(fromPosition, toPosition))) {
                throw new NotRightMoveException();
            }
        }
        if (this.playerNumber == PLAYER_NUMBER_TWO) {
            if (!(validatePlayerTwoBasicPawnMove(chessPieces, fromPosition, toPosition) || validatePlayerTwoCaptureMove(chessPieces, fromPosition, toPosition) || validatePlayerTwoFirstPawnMove(fromPosition, toPosition))) {
                throw new NotRightMoveException();
            }
        }
    }


    //TODO 이 코드의 중복은 아주 쉽게 해결할 수 있으나 지금은 정신력이 부족허다
    private boolean validatePlayerOneFirstPawnMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return fromPosition.getY() == 2 && toPosition.getY() == 4 && fromPosition.getX() == toPosition.getX();
    }

    private boolean validatePlayerOneCaptureMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getPlayerNumber() != playerNumber && Math.abs(fromPosition.getX() - toPosition.getX()) == 1 && fromPosition.getY() + 1 == toPosition.getY();
    }

    private boolean validatePlayerOneBasicPawnMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return chessPieces.get(toPosition) == null && fromPosition.getX() == toPosition.getX() && fromPosition.getY() + 1 == toPosition.getY();
    }

    private boolean validatePlayerTwoFirstPawnMove(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return fromPosition.getY() == 7 && toPosition.getY() == 5 && fromPosition.getX() == toPosition.getX();
    }

    private boolean validatePlayerTwoCaptureMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getPlayerNumber() != playerNumber && Math.abs(fromPosition.getX() - toPosition.getX()) == 1 && fromPosition.getY() - 1 == toPosition.getY();
    }

    private boolean validatePlayerTwoBasicPawnMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        return chessPieces.get(toPosition) == null && (fromPosition.getX() == toPosition.getX() && fromPosition.getY() - 1 == toPosition.getY());
    }
}

