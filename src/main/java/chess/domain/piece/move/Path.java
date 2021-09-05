package chess.domain.piece.move;

import chess.domain.board.Position;
import chess.domain.player.Player;

import java.util.*;

public class Path {

    private final List<Position> positions;

    public Path(Position position) {
        this(Collections.singletonList(position));
    }

    public Path(Position... positions) {
        this(Arrays.asList(positions));
    }

    public Path(List<Position> positions) {
        this.positions = Collections.unmodifiableList(new ArrayList<>(positions));
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    public Path getPositionsUntilTarget(Position target) {
        List<Position> positionsBeforeTarget = new ArrayList<>();

        for (Position position : positions) {
            if (position.isSame(target)) {
                break;
            }
                positionsBeforeTarget.add(position);
        }

        return new Path(positionsBeforeTarget);
    }

    public boolean isNotBlockedBy(Player player) {
        return positions.stream()
                .noneMatch(player::hasPieceOn);
    }

    public boolean isBlockedBy(Player player) {
        return positions.stream()
                .anyMatch(player::hasPieceOn);
    }

    public boolean isNotEmpty() {
        return !positions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return positions.equals(path.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
