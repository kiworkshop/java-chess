package ChessPieces;

public interface ChessPiece {
    String getPrintCode();

    ChessPiecePosition getPosition();

    boolean isMovable(ChessPiecePosition fromPosition, ChessPiecePosition toPosition);
}
