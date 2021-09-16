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

    public static File findBy(int fileNumber) {
        return Arrays.stream(File.values())
                .filter(rank -> rank.number == fileNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File을 찾을 수 없습니다."));
    }

    public static String getSymbols() {
        return Arrays.stream(values())
                .map(File::getSymbol)
                .collect(joining());
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }
}
