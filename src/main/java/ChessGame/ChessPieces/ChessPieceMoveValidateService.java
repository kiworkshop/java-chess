package ChessGame.ChessPieces;

import ChessGame.Exception.CannotJumptException;

import java.util.HashMap;

public class ChessPieceMoveValidateService {
    static void validateDiagonalJumpMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException {
        if (Math.abs(toPosition.getX() - fromPosition.getX()) == Math.abs(toPosition.getY() - fromPosition.getY())) {    //Diagonal이동
            if (chessPieces.entrySet().stream().anyMatch(e -> Math.abs(toPosition.getX() - e.getKey().getX()) == Math.abs(toPosition.getY() - e.getKey().getY()) && isBetween(fromPosition.getX(), toPosition.getX(), e.getKey().getX()))) {
                throw new CannotJumptException();
            }
        }
    }

    static void validateHorizontalJumpMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException {
        if (fromPosition.getY() == toPosition.getY()) {    //횡이동
            if (chessPieces.entrySet().stream().anyMatch(e -> e.getKey().getY() == fromPosition.getY() && isBetween(fromPosition.getX(), toPosition.getX(), e.getKey().getX()))) {
                throw new CannotJumptException();
            }
        }
    }


    static void validateVerticalJumpMove(HashMap<ChessPiecePosition, ChessPiece> chessPieces, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws CannotJumptException {
        if (fromPosition.getX() == toPosition.getX()) {    //종이동
            if (chessPieces.entrySet().stream().anyMatch(e -> e.getKey().getX() == fromPosition.getX() && isBetween(fromPosition.getY(), toPosition.getY(), e.getKey().getY()))) {
                throw new CannotJumptException();
            }
        }
    }

    public static boolean isBetween(int a, int b, int c) {
        return b > a ? c > a && c < b : c > b && c < a;
    }
}
