package chess.domain.piece;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private String filePosition;

    File(String filePosition) {
        this.filePosition = filePosition;
    }
}
