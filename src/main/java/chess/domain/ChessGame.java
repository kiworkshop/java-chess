package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.command.MoveOptions;

public class ChessGame {

    private final Board board = new Board();
    private boolean isRunning = true;
    private boolean isWhiteTurn = true;

    public ChessGame() {
    }

    public void move(final MoveOptions moveOptions) {
        board.move(moveOptions, isWhiteTurn);
        isWhiteTurn = !isWhiteTurn;

        if (board.isEnd()) {
            isRunning = false;
        }
    }

    public void end() {
        isRunning = false;
    }

    public Status getStatus() {
        return board.getStatus();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
