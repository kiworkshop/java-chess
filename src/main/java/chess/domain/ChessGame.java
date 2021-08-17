package chess.domain;

import chess.domain.board.Board;

public class ChessGame {

    private static final String START = "start";
    private static final String END = "end";

    private final Board board = new Board();
    private boolean isRunning = false;

    public ChessGame(final String initialCommand) {
        if (initialCommand.equals(START)) {
            isRunning = true;
            return;
        }

        if (initialCommand.equals(END)) {
            return;
        }

        throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
    }


    public boolean isRunning() {
        return isRunning;
    }

    public Board getBoard() {
        return board;
    }
}
