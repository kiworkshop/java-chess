package ChessPieces;

public interface ChessPiece {
    int PLAYER_NUMBER_ONE = 1;
    int PLAYER_NUMBER_TWO = 2;

    String getPrintCode();

    boolean isMovable(ChessPiecePosition fromPosition, ChessPiecePosition toPosition);
}
