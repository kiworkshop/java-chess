package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.command.MoveParameters;

public class ChessGame {

    private final Board board = new Board();
    private boolean isFinished = false;
    private boolean isWhiteTurn = true;

    public ChessGame() {
    }

    public void move(final MoveParameters moveParameters) {
        board.move(moveParameters, isWhiteTurn);
        isWhiteTurn = !isWhiteTurn;

        if (board.isEnd()) {
            end();
        }
    }

    public void end() {
        isFinished = true;
    }

    public Status getStatus() {
        return board.getStatus();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isRunning() {
        return !isFinished;
    }
}
