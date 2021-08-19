package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Team;
import chess.domain.position.Position;
import chess.game.Turn;

public class Ready implements GameState {
    private final Board board;

    public Ready() {
        this.board = Board.of(BoardInitializer.reset());
    }

    @Override
    public GameState start() {
        return new Playing(Turn.of(Team.WHITE));
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("게임이 시작되지 않아 종료할 수 없습니다.");
    }

    @Override
    public Turn turn() {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
