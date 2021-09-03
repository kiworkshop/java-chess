package chess.domain.player;

import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AttackPositions {

    private static final int EMPTY = 0;

    private final Map<Position, Integer> counts;

    public AttackPositions(final Map<Position, Piece> pieces) {
        this.counts = new HashMap<>();

        pieces.keySet()
                .forEach(position -> {
                    Piece piece = pieces.get(position);
                    Collection<Position> positions = piece.findAvailableAttackPositions(position);
                    positions.forEach(this::increase);
                });
    }

    public void update(final Position source, final Position target, final Piece piece) {
        remove(source, piece);
        add(target, piece);
    }

    public void remove(final Position position, final Piece piece) {
        Collection<Position> previousAttackPositions = piece.findAvailableAttackPositions(position);
        previousAttackPositions.forEach(this::decrease);
    }

    private void add(final Position position, final Piece piece) {
        Collection<Position> currentAttackPositions = piece.findAvailableAttackPositions(position);
        currentAttackPositions.forEach(this::increase);
    }

    private void decrease(final Position position) {
        counts.put(position, counts.get(position) - 1);
    }

    private void increase(final Position position) {
        counts.put(position, counts.getOrDefault(position, 0) + 1);
    }

    public boolean isEmpty(final Position position) {
        return !counts.containsKey(position) || (counts.get(position) == EMPTY);
    }

    public boolean contains(final Position position) {
        return counts.containsKey(position);
    }
}
