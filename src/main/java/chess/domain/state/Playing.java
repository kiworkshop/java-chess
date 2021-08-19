package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.game.Turn;

public class Playing implements GameState {
    private Turn turn;
    private Board board;

    public Playing(Turn turn) {
        this.turn = turn;
    }

    public Team toggle() {
        return turn.toggle();
    }

    public Turn getTurn() {
        return turn;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
