package chess;

import chess.controller.ChessController;
import chess.support.ChessMessageQueue;
import chess.view.console.ConsoleChessView;

public class ChessApplication {
    public static void main(String[] args) throws InterruptedException {
        ChessMessageQueue messageQueue = new ChessMessageQueue();
        Thread chessView = new Thread(new ConsoleChessView(messageQueue));
        Thread chessController = new Thread(new ChessController(messageQueue));

        chessView.start();
        chessController.start();

        chessView.join();
        chessController.join();
    }
}
