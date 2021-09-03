package chess.domain.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final Map<String, Command> COMMANDS = createCommands();

    private static Map<String, Command> createCommands() {
        Map<String, Command> commands = new HashMap<>();

        Arrays.stream(values())
                .forEach(command -> commands.put(command.command, command));

        return commands;
    }

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(final String command) {
        Command foundCommand = COMMANDS.get(command);

        if (foundCommand == null) {
            throw new IllegalArgumentException("유효하지 않은 명령어입니다.");
        }

        return foundCommand;
    }

    public void validateInitialCommand() {
        if (isStatus() || isMove()) {
            throw new IllegalArgumentException(String.format("%s 또는 %s를 입력해주세요.", START.command, END.command));
        }
    }

    public boolean isEnd() {
        return this.equals(END);
    }

    public boolean isMove() {
        return this.equals(MOVE);
    }

    public boolean isStatus() {
        return this.equals(STATUS);
    }
}
