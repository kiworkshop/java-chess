package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Finish implements GameState {
    private final Board board;

    public Finish() {
        this.board = Board.of(Collections.emptyMap());
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 종료되어 다시 게임을 시작 할 수 없습니다.");
    }

    @Override
    public GameState move(Piece source, Piece target) {
        throw new UnsupportedOperationException("게임이 종료되어 체스 말을 움직일 수 없습니다.");
    }

    @Override
    public Turn toggle() {
        throw new UnsupportedOperationException("게임이 종료되어 턴을 변경할 수 없습니다.");
    }

    @Override
    public double status(Team team) {
        List<Piece> pieces = board.findBy(team);
        return Score.calculateSum(pieces);
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public List<String> winner(Score score) {
        ArrayList<String> result = new ArrayList<>();
        double whiteScore = score.white();
        double blackScore = score.black();

        if (whiteScore >= blackScore) {
            result.add(File.getSymbols());
        }
        if (whiteScore <= blackScore) {
            result.add(File.getSymbols().toUpperCase());
        }
        return result;
    }

    @Override
    public Turn getTurn() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
