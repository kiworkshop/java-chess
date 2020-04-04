package chess.domain;

public enum UserCommand {
    START,
    END,
    MOVE;

    public boolean isStart() {
        return this.equals(UserCommand.START);
    }

    public boolean isEnd() {
        return this.equals(UserCommand.END);
    }

    public boolean isNotMove() {
        return !this.equals(UserCommand.MOVE);
    }

    public static UserCommand of(String userCommand) {
        return valueOf(userCommand.toUpperCase());
    }

    public static UserCommand of(String[] commandSplit) {
        return valueOf(commandSplit[0].toUpperCase());
    }

    public static boolean isInvalidMoveCommand(String[] commandSplit) {
        return commandSplit.length != 3;
    }
}
