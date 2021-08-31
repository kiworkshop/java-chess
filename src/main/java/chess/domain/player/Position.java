package chess.domain.player;

import chess.domain.board.File;
import chess.domain.board.Rank;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Position {
    private static final Map<String, Position> POSITIONS = createPositions();

    private static Map<String, Position> createPositions() {
        Map<String, Position> positions = new HashMap<>();

        Arrays.stream(File.values())
                .forEach(file -> Arrays.stream(Rank.values())
                        .forEach(rank -> positions.put(createKey(file, rank), new Position(file, rank))));

        return positions;
    }

    private static String createKey(final File file, final Rank rank) {
        return file.name() + rank.getIndex();
    }

    public static Position of(final String key) {
        return POSITIONS.get(key);
    }

    public static Position from(final File file, final Rank rank) {
        return of(createKey(file, rank));
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int calculateFileGap(final Position position) {
        return file.calculateGap(position.getFile());
    }

    public int calculateRankGap(final Position position) {
        return rank.calculateGap(position.getRank());
    }

    public Set<Position> findPassingPositions(Position target, MoveCoordinate moveCoordinate) {
        Set<Position> positions = new HashSet<>();
        Position current = this;

        while (!target.equals(current)) {
            current = current.move(moveCoordinate);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    public Collection<Position> findAvailablePositions(final MoveCoordinate moveCoordinate, final boolean isFinite) {
        if (isFinite) {
            return getFinitePositions(moveCoordinate);
        }
        return getInfinitePositions(moveCoordinate);
    }

    private Collection<Position> getFinitePositions(MoveCoordinate moveCoordinate) {
        if (isMovable(moveCoordinate)) {
            return Collections.singleton(move(moveCoordinate));
        }
        return Collections.emptySet();
    }

    private Collection<Position> getInfinitePositions(MoveCoordinate moveCoordinate) {
        Collection<Position> positions = new HashSet<>();

        Position current = this;
        while (current.isMovable(moveCoordinate)) {
            current = current.move(moveCoordinate);
            positions.add(current);
        }

        return positions;
    }

    private boolean isMovable(final MoveCoordinate moveCoordinate) {
        return rank.canMove(moveCoordinate.getRank()) && file.canMove(moveCoordinate.getFile());
    }

    private Position move(final MoveCoordinate moveCoordinate) {
        File file = this.file.add(moveCoordinate.getFile());
        Rank rank = this.rank.add(moveCoordinate.getRank());
        return Position.from(file, rank);
    }

    public boolean hasSameRank(Rank rank) {
        return this.rank == rank;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
