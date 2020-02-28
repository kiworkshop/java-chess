package mychess.domain;

public enum Position {

    A8("a8"), B8("b8"), C8("c8"), D8("d8"), E8("e8"), F8("f8"), G8("g8"), H8("h8"),
    A7("a7"), B7("b7"), C7("c7"), D7("d7"), E7("e7"), F7("f7"), G7("g7"), H7("h7"),
    A6("a6"), B6("b6"), C6("c6"), D6("d6"), E6("e6"), F6("f6"), G6("g6"), H6("h6"),
    A5("a5"), B5("b5"), C5("c5"), D5("d5"), E5("e5"), F5("f5"), G5("g5"), H5("h5"),
    A4("a4"), B4("b4"), C4("c4"), D4("d4"), E4("e4"), F4("f4"), G4("g4"), H4("h4"),
    A3("a3"), B3("b3"), C3("c3"), D3("d3"), E3("e3"), F3("f3"), G3("g3"), H3("h3"),
    A2("a2"), B2("b2"), C2("c2"), D2("d2"), E2("e2"), F2("f2"), G2("g2"), H2("h2"),
    A1("a1"), B1("b1"), C1("c1"), D1("d1"), E1("e1"), F1("f1"), G1("g1"), H1("h1");

    private static final String MAX_WIDTH_INDEX = "h";
    private static final String FRONT_LINE_INDEX_OF_BLACK = "7";
    private static final String BACK_LINE_INDEX_OF_BLACK = "8";
    private static final String FRONT_LINE_INDEX_OF_WHITE = "2";
    private static final String BACK_LINE_INDEX_OF_WHITE = "1";

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public boolean isEndOfLine() {
        return position.contains(MAX_WIDTH_INDEX);
    }

    public boolean isFrontLineOfBlack() {
        return position.contains(FRONT_LINE_INDEX_OF_BLACK);
    }

    public boolean isBackLineOfBlack() {
        return position.contains(BACK_LINE_INDEX_OF_BLACK);
    }

    public boolean isFrontLineOfWhite() {
        return position.contains(FRONT_LINE_INDEX_OF_WHITE);
    }

    public boolean isBackLineOfWhite() {
        return position.contains(BACK_LINE_INDEX_OF_WHITE);
    }
}
