package chess.view.console;

import chess.controller.dto.ChessRequest;
import chess.view.ChessInput;

import java.util.Scanner;

public class ChessInputConsole implements ChessInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public ChessRequest getUserRequest() {
        while (true) {
            System.out.println("입력> ");
            String[] input = scanner.nextLine().split(" ");
            ChessRequest request = createChessRequest(input);
            if (request == null) {
                System.out.println("잘못된 입력값. 다시 입력하세요.");
            } else {
                return request;
            }
        }
    }

    private ChessRequest createChessRequest(String[] input) {
        if (input.length == 1 && input[0].equalsIgnoreCase("start")) {
            return ChessRequest.of(ChessRequest.Action.START);

        } else if (input.length == 1 && input[0].equalsIgnoreCase("end")) {
            return ChessRequest.of(ChessRequest.Action.END);

        } else if (input.length == 3 && input[0].equalsIgnoreCase("move")) {
            return ChessRequest.of(ChessRequest.Action.MOVE, input[1], input[2]);
        } else {
            return null;
        }
    }
}
