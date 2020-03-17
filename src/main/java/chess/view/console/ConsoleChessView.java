package chess.view.console;

import chess.support.ChessMessageQueue;
import chess.view.ChessView;

public class ConsoleChessView extends ChessView {
    public ConsoleChessView(ChessMessageQueue messageQueue) {
        super(new ConsoleChessInput(), new ConsoleChessOutput(), messageQueue);
    }
}
