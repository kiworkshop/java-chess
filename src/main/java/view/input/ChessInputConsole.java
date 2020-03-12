package view.input;

import java.util.Scanner;

public class ChessInputConsole implements ChessInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String requestUserInput() {
        System.out.println("입력> ");
        return scanner.nextLine();
    }
}
