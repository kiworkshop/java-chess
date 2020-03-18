package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;

import java.util.HashMap;

public interface ChessPiece {
    int PLAYER_NUMBER_ONE = 1;
    int PLAYER_NUMBER_TWO = 2;

    int getPlayerNumber();

    String getPrintCode();

    boolean isMovable(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException;
}
