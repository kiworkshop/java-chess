package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.game.Score;
import chess.game.Turn;

import java.util.List;

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
    public GameState move(Piece source, Piece target) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 체스 말을 움직일 수 없습니다.");
    }

    @Override
    public Turn toggle() {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 턴을 변경할 수 없습니다.");
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
    public List<String> winner(Score score) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않아 우승자를 가릴 수 없습니다.");
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
