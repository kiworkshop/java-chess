package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.game.Turn;

public class Playing implements GameState {
    private Turn turn;
    private Board board;

    public Playing(Turn turn) {
        this.turn = turn;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        board.move(source, target);
        turn = turn.toggle();
        return this;
    }

    @Override
    public GameState end() {
        return null;
    }

    @Override
    public Turn turn() {
        return turn;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
