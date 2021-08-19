package chess.domain.state;

import chess.domain.board.Board;

import java.util.LinkedHashMap;

public class Finish implements GameState {
    @Override
    public Board getBoard() {
        return Board.of(new LinkedHashMap<>());
    }
}
