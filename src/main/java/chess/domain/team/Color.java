package chess.domain.team;

public enum Color {

    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Color flip() {
        if (isWhite()) {
            return BLACK;
        }
        return WHITE;
    }
}
