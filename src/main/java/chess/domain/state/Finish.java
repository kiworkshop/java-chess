package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.game.Turn;

public class Finish implements GameState {
    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 게임이 종료되어 다시 게임을 시작 할 수 없습니다.");
    }

    @Override
    public GameState moveAndToggleTurn(Piece source, Piece target) {
        throw new UnsupportedOperationException("이미 게임이 종료되어 체스 말을 움직일 수 없습니다.");
    }

    @Override
    public double status(Team team) {
        throw new UnsupportedOperationException("이미 게임이 종료되어 상태를 확인할 수 없습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Turn turn() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Board board() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }
}
