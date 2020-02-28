package mychess;

import mychess.controller.ChessController;
import mychess.view.OutputView;
import mychess.view.InputView;

public class ChessApplication {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new InputView(), new OutputView());
        chessController.run();
    }
}
