package chess.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Position {

    private static final List<Row> WHITE_ROWS = Arrays.asList(Row.EIGHT, Row.SEVEN);
    private static final List<Row> BLACK_ROWS = Arrays.asList(Row.ONE, Row.TWO);

    static final Map<String, Position> positionMap;

    Column column;
    Row row;

    static {
        positionMap = new HashMap<>();

        for (Column column : Column.values()) {
            addPosition(column);
        }
    }

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(String positionName) {
        Optional<Position> position = Optional.ofNullable(positionMap.get(positionName));
        return position.orElseThrow(() -> new IllegalArgumentException("잘못된 위치를 선택했습니다"));
    }

    public int rowDistance(Position targetPosition) {
        return this.row.differ(targetPosition.row);
    }

    public int colDistance(Position targetPosition) {
        return this.column.differ(targetPosition.column);
    }

    private static void addPosition(Column column) {
        for (Row row : Row.values()) {
            String position = column.getName() + row.getValue();
            positionMap.put(position, new Position(column, row));
        }
    }

    boolean isStartingPosition() {
        return row.isStartingPoint();
    }

    boolean isWhiteStartingPosition() {
        return row.isStartingPoint() && WHITE_ROWS.contains(row);
    }

}
