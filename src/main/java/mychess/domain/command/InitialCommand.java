package mychess.domain.command;

public class InitialCommand extends AbstractCommand{

    public InitialCommand(String command) {
        super(command);
        if (isMoveCommand()) throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        if (!validate(command)) throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
