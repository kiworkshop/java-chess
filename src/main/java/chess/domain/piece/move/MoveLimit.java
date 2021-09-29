package chess.domain.piece.move;

public enum MoveLimit {

    FINITE(true),
    INFINITE(false);

    private final boolean isFinite;

    MoveLimit(boolean isFinite) {
        this.isFinite = isFinite;
    }

    public boolean isFinite() {
        return isFinite;
    }
}
