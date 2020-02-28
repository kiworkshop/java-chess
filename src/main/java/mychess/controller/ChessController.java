package mychess.controller;

import mychess.domain.ChessGame;
import mychess.domain.Player;
import mychess.view.InputView;
import mychess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame(new Player(true), new Player(false));
        chessGame.processCommand(inputView.getInitialCommand());
        outputView.printBoard(chessGame.getBoard());

        while (true) {
            chessGame.processCommand(inputView.getPlayerCommand());
            outputView.printBoard(chessGame.getBoard());
        }
    }
}
