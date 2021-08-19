package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.game.Turn;

import java.util.LinkedHashMap;

public class Finish implements GameState {
    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다. 체스말을 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        return new Finish();
    }

    @Override
    public Turn turn() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Board getBoard() {
        return Board.of(new LinkedHashMap<>());
    }
}
