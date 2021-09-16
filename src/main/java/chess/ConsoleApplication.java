package chess;

import chess.controller.ChessController;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        OutputView outputView = new ConsoleOutputView();
        InputView inputView = new ConsoleInputView();
        ChessController chessController = new ChessController(inputView, outputView);
        chessController.run();
    }
}
