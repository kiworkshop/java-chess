package chess.domain.board;

public enum Row {

    ONE(1, true),
    TWO(2, true),
    THREE(3, false),
    FOUR(4, false),
    FIVE(5, false),
    SIX(6, false),
    SEVEN(7, true),
    EIGHT(8, true);

    private final int value;
    private final boolean startingPoint;

    Row(int value, boolean startingPoint) {
        this.value = value;
        this.startingPoint = startingPoint;
    }

    public String getValue() {
        return String.valueOf(value);
    }

    public boolean isStartingPoint() {
        return startingPoint;
    }

    public int differ(Row row) {
        return Math.abs(this.value - row.value);
    }

}
