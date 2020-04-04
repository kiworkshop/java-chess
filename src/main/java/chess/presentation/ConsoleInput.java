package chess.presentation;

import chess.domain.UserCommand;

import java.util.Scanner;

public class ConsoleInput {

    public static final Scanner scanner = new Scanner(System.in);

    public static UserCommand getUserCommand() {
        String[] commandSplit = getUserCommandSplit();
        return UserCommand.of(commandSplit[0]);
    }

    public static String[] getUserCommandSplit() {
        return getUserInput().split(" ");
    }

    private static String getUserInput() {
        return scanner.next();
    }
}
