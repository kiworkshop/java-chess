package chess;

import chess.controller.ChessController;
import chess.view.ConsoleOutputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        OutputView outputView = new ConsoleOutputView();
        ChessController chessController = new ChessController(outputView);
        chessController.run();
    }
}
