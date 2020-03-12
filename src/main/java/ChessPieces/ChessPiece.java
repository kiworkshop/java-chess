package ChessPieces;

public interface ChessPiece {
    String getPrintCode();

    ChessPiecePosition getPosition();

    void move(ChessPiecePosition toPosition);
}
