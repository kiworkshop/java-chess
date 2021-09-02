package chess.domain.player;

import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AttackRange {

    private static final int EMPTY = 0;
    private static final int COUNT_UNIT = 1;

    private final Map<Position, Integer> counts = new HashMap<>();

    public AttackRange(final Map<Position, Piece> pieces) {
        pieces.forEach((position, piece) -> {
            Collection<Position> positions = piece.findAvailableAttackPositions(position);
            positions.forEach(this::increase);
        });
    }

    private void increase(final Position position) {
        counts.put(position, counts.getOrDefault(position, EMPTY) + COUNT_UNIT);
    }

    public boolean contains(final Position position) {
        return counts.containsKey(position) && (counts.get(position) > EMPTY);
    }

    public void update(final Position source, final Position target, final Piece piece) {
        remove(source, piece);
        add(target, piece);
    }

    public void remove(final Position position, final Piece piece) {
        Collection<Position> previousAttackPositions = piece.findAvailableAttackPositions(position);
        previousAttackPositions.forEach(this::decrease);
    }

    private void decrease(final Position position) {
        counts.put(position, counts.get(position) - COUNT_UNIT);
    }

    private void add(final Position position, final Piece piece) {
        Collection<Position> currentAttackPositions = piece.findAvailableAttackPositions(position);
        currentAttackPositions.forEach(this::increase);
    }
}
