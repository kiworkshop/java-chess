package chess;

import chess.controller.ConsoleChessController;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        OutputView outputView = new ConsoleOutputView();
        InputView inputView = new ConsoleInputView();
        ConsoleChessController consoleChessController = new ConsoleChessController(inputView, outputView);
        consoleChessController.run();
    }
}
