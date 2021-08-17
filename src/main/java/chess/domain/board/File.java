package chess.domain.board;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
