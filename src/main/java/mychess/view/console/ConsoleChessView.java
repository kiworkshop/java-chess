package mychess.view.console;

import mychess.support.ChessMessageQueue;
import mychess.view.ChessView;

public class ConsoleChessView extends ChessView {
    public ConsoleChessView(ChessMessageQueue messageQueue) {
        super(new ConsoleChessInput(), new ConsoleChessOutput(), messageQueue);
    }
}
