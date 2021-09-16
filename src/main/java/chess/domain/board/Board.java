package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.game.Turn;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(final Map<Position, Piece> board) {
        return new Board(board);
    }

    public Map<Position, Piece> move(Piece source, Piece target) {
        board.put(source.getPosition(), Blank.of(source.getPosition()));
        board.put(target.getPosition(), source);
        source.move(target.getPosition());
        return board;
    }

    public List<Piece> findBy(Team team) {
        return board.values().stream()
                .filter(piece -> piece.getTeam().equals(team))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> values() {
        return Collections.unmodifiableMap(board);
    }

    public Piece from(Position position) {
        return board.get(position);
    }

    public boolean existKing(Turn turn) {
        List<Piece> pieces = findBy(turn.team());
        Optional<Piece> existKing = pieces.stream().filter(King.class::isInstance).findFirst();
        return existKing.isPresent();
    }

    public boolean isOtherTeam(Position source, Position target) {
        return isNotSameTeam(source, target) && !isBlank(target);
    }

    public boolean isNotSameTeam(Position source, Position target) {
        Piece sourcePiece = from(source);
        Piece targetPiece = from(target);
        return !sourcePiece.getTeam().isSameTeam(targetPiece.getTeam());
    }

    public boolean isBlank(Position target) {
        Piece targetPiece = from(target);
        return targetPiece.getTeam().equals(Team.NEUTRAL);
    }
}
