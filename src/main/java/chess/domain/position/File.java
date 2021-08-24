package chess.domain.position;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int number;

    File(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static String symbols() {
        return Arrays.stream(values())
                .map(file -> file.symbol())
                .collect(joining());
    }

    public String symbol() {
        return symbol;
    }

    public int number() {
        return number;
    }
}
