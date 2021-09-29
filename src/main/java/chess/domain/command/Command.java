package chess.domain.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final String DELIMITER = " ";
    private static final int OPERATION_INDEX = 0;

    private final Operation operation;
    private final List<String> parameters;

    public Command(final String commandLine) {
        List<String> chunks = Arrays.stream(commandLine.split(DELIMITER))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (chunks.isEmpty()) {
            throw new IllegalArgumentException("명령어를 입력해주세요.");
        }

        this.operation = Operation.of(chunks.get(OPERATION_INDEX));
        this.parameters = chunks.subList(OPERATION_INDEX + 1, chunks.size());
    }

    public MoveParameters getMoveParameters() {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("기물 이동 위치를 정확하게 입력해주세요.");
        }

        return new MoveParameters(parameters);
    }

    public boolean isStart() {
        return operation.isStart();
    }

    public boolean isEnd() {
        return operation.isEnd();
    }

    public boolean isMove() {
        return operation.isMove();
    }

    public boolean isStatus() {
        return operation.isStatus();
    }
}
