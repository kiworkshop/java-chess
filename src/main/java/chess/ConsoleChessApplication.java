package chess;

import chess.controller.ChessGameController;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGameController controller = new ChessGameController();

        OutputView.printStartMessage();
        String startInput = InputView.getUserInput();
        if (!startInput.equals("start")) {
            throw new IllegalArgumentException("체스 게임을 시작하려면 start를 입력해 주세요.");
        }
        controller.gameStart();

        String input = InputView.getUserInput();
        while (!input.equals("end") || !input.equals("state")) {
            String[] moveInput = InputView.parseMoveInput(input);
            controller.movePiece(moveInput[0], moveInput[1]);
            input = InputView.getUserInput();
        }
    }

}
