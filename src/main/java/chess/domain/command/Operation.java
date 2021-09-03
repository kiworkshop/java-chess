package chess.domain.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Operation {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final Map<String, Operation> operationNames = createMap();

    private static Map<String, Operation> createMap() {
        Map<String, Operation> map = new HashMap<>();

        Arrays.stream(Operation.values())
                .forEach(operation -> map.put(operation.keyword, operation));

        return Collections.unmodifiableMap(map);
    }

    public static Operation of(String input) {
        Operation operation = operationNames.get(input);

        if (operation == null) {
            throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
        }

        return operation;
    }

    private final String keyword;

    Operation(String keyword) {
        this.keyword = keyword;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
