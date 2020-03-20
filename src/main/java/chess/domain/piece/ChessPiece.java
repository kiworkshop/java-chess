package chess.domain.piece;

public abstract class ChessPiece implements Movable {

    protected final boolean white;
    protected char name;

    protected ChessPiece(boolean white) {
        this.white = white;
    }
}
