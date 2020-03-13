package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessBoardPosition {

    // Todo to enum
    public static final Map<String, Integer> alphabetNthRowPair = new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
            put("d", 4);
            put("e", 5);
            put("f", 6);
            put("g", 7);
            put("h", 8);
    }};

    private int nthRow;
    private int nthColumn;

    private ChessBoardPosition(int nthRow, int nthColumn) {
        valid(nthRow, nthColumn);
        this.nthRow = nthRow;
        this.nthColumn = nthColumn;
    }

    public static ChessBoardPosition from(int nthRow, int nthColumn) {
        return new ChessBoardPosition(nthRow, nthColumn);
    }

    public static ChessBoardPosition from(String position) {
        int nthRow = parseAlphabetToNthRow(position.substring(0, 1));
        int nthColumn = Character.getNumericValue(position.charAt(1));
        return new ChessBoardPosition(nthRow, nthColumn);
    }

    private static int parseAlphabetToNthRow(String alphabet) {
        if (!alphabetNthRowPair.containsKey(alphabet)) {
            throw new IllegalArgumentException("move 명령어의 알파벳은 a~h까지 입니다.");
        }
        return alphabetNthRowPair.get(alphabet);
    }

    // Todo 매직 넘버
    private void valid(int rawPosition, int columnPosition) {
        if (rawPosition < 1 | rawPosition > 8 | columnPosition <  1 | columnPosition > 8) {
            throw new IllegalArgumentException();
        }
    }

    public int getNthRow() {
        return nthRow;
    }

    public int getNthColumn() {
        return nthColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoardPosition that = (ChessBoardPosition) o;
        return nthRow == that.nthRow &&
                nthColumn == that.nthColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nthRow, nthColumn);
    }
}
