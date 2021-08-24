package chess;

import chess.controller.ChessGameController;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGameController controller = new ChessGameController();
        controller.run();
    }
}
