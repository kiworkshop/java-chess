package chess.domain;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.MoveParameters;

public class ChessGame {

    private final Board board = new Board();
    private boolean isRunning = true;
    private boolean isWhiteTurn = true;

    public ChessGame() {
    }

    public void run(final Command command) {
        if (command.isStart()) {
            return;
        }

        if (command.isEnd()) {
            isRunning = false;
            return;
        }

        if (command.isMove()) {
            move(command.getMoveParameters());
            return;
        }

        throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
    }

    private void move(final MoveParameters moveParameters) {
        board.move(moveParameters, isWhiteTurn);
        isWhiteTurn = !isWhiteTurn;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Board getBoard() {
        return board;
    }
}
