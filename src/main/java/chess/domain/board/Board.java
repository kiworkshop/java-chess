package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
    }

    public Map<Position, Piece> move(Piece source, Piece target) {
        board.put(source.position(), Blank.of(source.position()));
        board.put(target.position(), source);
        source.move(target.position());
        return  board;
    }

    public static List<Piece> findBy(Team team) {
        return board.values().stream()
                .filter(piece -> piece.team().equals(team))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> values() {
        return Collections.unmodifiableMap(board);
    }

    public Piece from(Position position) {
        return board.get(position);
    }
}
