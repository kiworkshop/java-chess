package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;
import ChessGame.PlayerNumber;

import java.util.HashMap;

public interface ChessPiece {
    PlayerNumber getPlayerNumber();

    String getPrintCode();

    void validateEachPieceMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException, Exception;
}
