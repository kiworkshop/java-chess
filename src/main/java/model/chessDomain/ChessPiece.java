package model.chessDomain;

public class ChessPiece {
    private final PieceColor color;
    private final PieceType type;

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public ChessPiece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }
}
