package chess;

import chess.controller.ChessGameController;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGameController controller = new ChessGameController();

        OutputView.printStartMessage();
        String userInput = InputView.getUserInput();
        if (!userInput.equals("start")) {
            throw new IllegalArgumentException("체스 게임을 시작하려면 start를 입력해 주세요.");
        }
        controller.gameStart();
    }
}
