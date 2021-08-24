package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.game.Score;
import chess.game.Turn;

import java.util.List;

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
        List<Piece> pieces = Board.findBy(team);
        return Score.calculateSum(pieces);
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    @Override
    public String winner(Score score) {
        String result = "";
        double whiteScore = score.white();
        double blackScore = score.black();

        if (whiteScore >= blackScore) {
            result += File.symbols();
        }
        if (whiteScore <= blackScore) {
            result += System.lineSeparator();
            result += File.symbols().toUpperCase();
        }
        return result;
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
