package chess.domain.board;

public enum Rank {
    R8(8),
    R7(7),
    R6(6),
    R5(5),
    R4(4),
    R3(3),
    R2(2),
    R1(1);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }


}

