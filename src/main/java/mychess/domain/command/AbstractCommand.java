package mychess.domain.command;

public abstract class AbstractCommand {

    private static final String COMMAND_END_GAME = "end";
    private static final String COMMAND_START_GAME = "start";
    private static final String COMMAND_MOVE_PIECE = "move";

    protected String command;

    public AbstractCommand(String command) {
        this.command = command;
    }

    public boolean isEndCommand() {
        return command.equals(COMMAND_END_GAME);
    }

    public boolean isStartCommand() {
        return command.equals(COMMAND_START_GAME);
    }

    public boolean isMoveCommand() {
        return command.equals(COMMAND_MOVE_PIECE);
    }

    protected boolean validate(String command) {
        return command.equals(COMMAND_END_GAME) || command.equals(COMMAND_START_GAME);
    }
}
