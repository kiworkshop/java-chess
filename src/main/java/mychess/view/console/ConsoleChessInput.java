package mychess.view.console;

import mychess.controller.Command;
import mychess.controller.dto.ChessRequest;
import mychess.view.ChessInput;

import java.util.Scanner;

public class ConsoleChessInput implements ChessInput {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_GET_INPUT = "입력> ";

    @Override
    public ChessRequest read() {
        System.out.println(Command.INSTRUCTION);
        System.out.print(MESSAGE_GET_INPUT);
        String query = SCANNER.nextLine();
        return ConsoleQueryProcessor.parse(query);
    }
}
