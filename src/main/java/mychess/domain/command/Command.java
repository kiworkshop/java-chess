package mychess.domain.command;

import mychess.domain.Position;

import java.util.EnumMap;

public class Command extends AbstractCommand{

    private static final String COMMAND_DELIMETER = " ";

    private String sourcePosition;
    private String targetPosition;

    public Command(String command) {
        super(command);
        String[] commandElements = command.split(COMMAND_DELIMETER);
        try {
            this.command = commandElements[0];
            if(!isStartCommand() && !isEndCommand() && !isMoveCommand()) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
            if(isStartCommand() || isEndCommand()) { initInitialCommand(); }
            if(isMoveCommand()) { initCommand(commandElements); }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void initInitialCommand() {
        this.sourcePosition = "";
        this.targetPosition = "";
    }

    private void initCommand(String[] commandElements) {
        this.sourcePosition = commandElements[1];
        this.targetPosition = commandElements[2];
        validatePosition(sourcePosition);
        validatePosition(targetPosition);
    }

    private void validatePosition(String position) {
        if (!Position.hasPosition(position)) throw new IllegalArgumentException("잘못된 위치 값입니다.");
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }
}
