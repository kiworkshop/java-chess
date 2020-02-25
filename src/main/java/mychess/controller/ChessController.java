package mychess.controller;

import mychess.domain.ChessGame;
import mychess.view.InputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String userCommand = inputView.getUserCommand();

        ChessGame chessGame = new ChessGame();
        chessGame.processCommand(userCommand);

    }
}
