package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.game.Turn;

public class Playing implements GameState {
    private Turn turn;
    private Board board;

    public Playing(Turn turn, Board board) {
        this.turn = turn;
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public GameState moveAndToggleTurn(Piece source, Piece target) {
        if (source.canMove(source, target)) {
            source.move(target.position());
            turn = turn.toggle();
        }
        return this;
    }

    @Override
    public GameState end() {
        return new Finish();
    }

    @Override
    public Turn turn() {
        return turn;
    }

    @Override
    public Board board() {
        return board;
    }
}
