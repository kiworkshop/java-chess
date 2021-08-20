package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AttackPositions {

    private final Map<Position, Integer> counts = new HashMap<>();

    public AttackPositions(final Map<Position, Piece> pieces) {
        pieces.keySet()
                .forEach(position -> {
                    Piece piece = pieces.get(position);
                    Collection<Position> positions = piece.findAvailableAttackPositions(position);
                    positions.forEach(target
                            -> counts.put(target, counts.getOrDefault(target, 0) + 1));
                });
    }

    public void update(final Position source, final Position target) {
//        Collection<Position> positions = source.findAvailablePositions();
    }
}
