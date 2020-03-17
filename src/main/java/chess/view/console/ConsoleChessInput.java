package chess.view.console;

import chess.controller.dto.ChessRequest;
import chess.view.ChessInput;

import java.util.Scanner;

public class ConsoleChessInput implements ChessInput {
    public static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public ChessRequest read() {
        System.out.print("입력하세요: ");
        String query = SCANNER.nextLine();
        return ConsoleQueryProcessor.parse(query);
    }
}
