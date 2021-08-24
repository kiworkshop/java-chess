package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.game.Turn;

import java.util.Collections;
import java.util.Map;

public class Ready implements GameState {
    private final Board board;

    public Ready() {
        this.board = Board.of(BoardInitializer.reset());
    }

    @Override
    public GameState start() {
        return new Playing(Turn.of(Team.WHITE), board);
    }

    @Override
    public GameState moveAndToggleTurn(Piece source, Piece target) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 체스 말을 움직일 수 없습니다.");
    }

    @Override
    public double status(Team team) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 상태를 확인할 수 없습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 종료할 수 없습니다.");
    }

    @Override
    public Turn turn() {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public Board board() {
        return board;
    }
}
