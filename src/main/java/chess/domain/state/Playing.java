package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.game.Score;
import chess.game.Turn;

import java.util.List;

public class Playing implements GameState {
    private Turn turn;
    private Board board;

    public Playing(Turn turn, Board board) {
        this.turn = turn;
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 이미 시작되어 다시 게임을 시작할 수 없었습니다.");
    }

    @Override
    public GameState move(Piece source, Piece target) {
        turn.checkTurn(source.team());
        if (source.canMove(source, target)) {
            board.move(source, target);
            return this;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    @Override
    public Turn toggle() {
        this.turn = turn.toggle();
        return turn;
    }

    @Override
    public double status(Team team) {
        List<Piece> pieces = Board.findBy(team);
        return Score.calculateSum(pieces);
    }

    @Override
    public List<String> winner(Score score) {
        throw new UnsupportedOperationException("아직 게임 진행을 진행하고 있어 우승자를 가릴 수 없습니다.");
    }

    @Override
    public GameState end() {
        return new Finish(board);
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
