package chess.domain;

public class ChessBoardPosition {

    private Row rowAt;
    private Column columnAt;

    private ChessBoardPosition(Row rowAt, Column columnAt) {
//        valid(rowAt, columnAt);
        this.rowAt = rowAt;
        this.columnAt = columnAt;
    }

    public static ChessBoardPosition with(Row rowAt, Column columnAt) {
        return new ChessBoardPosition(rowAt, columnAt);
    }

    // Todo 매직 넘버
    private void valid(int rowAt, String columnAt) {
//        if (rowNum < 1 | rowNum > 8 | columnNum <  1 | columnNum > 8) {
//            throw new IllegalArgumentException();
//        }
    }
}
