package chess.domain;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private String columnAt;

    Column(String columnAt) {
        this.columnAt = columnAt;
    }
}
