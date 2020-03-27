package chess;

import chess.controller.ChessController;
import chess.view.console.ChessInputConsole;
import chess.view.console.ChessOutputConsole;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = ChessController.of(
                        new ChessInputConsole(),
                        new ChessOutputConsole()
        );
        chessController.run();
    }
}
