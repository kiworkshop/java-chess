package chess.domain.board;

public enum Column {

    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8);

    private final String name;
    private final int value;

    Column(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int differ(Column column) {
        return Math.abs(this.value - column.value);
    }
}
