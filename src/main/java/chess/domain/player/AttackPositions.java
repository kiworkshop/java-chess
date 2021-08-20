package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AttackPositions {

    private static final int EMPTY = 0;

    private final Map<Position, Integer> counts = new HashMap<>();

    public AttackPositions(final Map<Position, Piece> pieces) {
        pieces.keySet()
                .forEach(position -> {
                    Piece piece = pieces.get(position);
                    Collection<Position> positions = piece.findAvailableAttackPositions(position);
                    positions.forEach(this::increase);
                });
    }

    public void update(final Position source, final Position target, final Piece piece) {
        Collection<Position> previousAttackPositions = piece.findAvailableAttackPositions(source);
        previousAttackPositions.forEach(this::decrease);
        Collection<Position> currentAttackPositions = piece.findAvailableAttackPositions(target);
        currentAttackPositions.forEach(this::increase);
    }

    private Integer increase(final Position target) {
        return counts.put(target, counts.getOrDefault(target, 0) + 1);
    }

    private Integer decrease(final Position position) {
        return counts.put(position, counts.get(position) - 1);
    }

    public boolean isEmpty(final Position position) {
        return !counts.containsKey(position) || (counts.get(position) == EMPTY);
    }

    public boolean contains(Position target) {
        return counts.containsKey(target);
    }
}
