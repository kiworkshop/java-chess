package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;

public class Ready implements GameState {
    private final Board board;

    public Ready() {
        this.board = Board.of(BoardInitializer.reset());
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
