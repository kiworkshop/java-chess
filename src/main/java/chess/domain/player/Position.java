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
import java.util.function.Consumer;

public class Position {
    private static final Map<String, Position> POSITIONS = createPositions();

    private static Map<String, Position> createPositions() {
        Map<String, Position> positions = new HashMap<>();

        Arrays.stream(File.values())
                .forEach(put(positions));

        return positions;
    }

    private static Consumer<File> put(final Map<String, Position> positions) {
        return file -> Arrays.stream(Rank.values())
                .map(rank -> new Position(file, rank))
                .forEach(position -> positions.put(createKey(position), position));
    }

    private static String createKey(final Position position) {
        return createKey(position.file, position.rank);
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

    public Set<Position> findPassingPositions(final Position target, final MoveCoordinate moveCoordinate) {
        Set<Position> positions = new HashSet<>();
        Position current = this;

        while (target.isDifferent(current)) {
            current = current.move(moveCoordinate);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    private boolean isDifferent(final Position current) {
        return !this.equals(current);
    }

    public Collection<Position> findAvailablePositions(final MoveCoordinate moveCoordinate, final boolean isFinite) {
        if (isFinite) {
            return getFinitePositions(moveCoordinate);
        }

        return getInfinitePositions(moveCoordinate);
    }

    private Collection<Position> getFinitePositions(final MoveCoordinate moveCoordinate) {
        if (isMovable(moveCoordinate)) {
            return Collections.singleton(move(moveCoordinate));
        }

        return Collections.emptySet();
    }

    private Collection<Position> getInfinitePositions(final MoveCoordinate moveCoordinate) {
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
        File movedFile = this.file.add(moveCoordinate.getFile());
        Rank movedRank = this.rank.add(moveCoordinate.getRank());
        return Position.from(movedFile, movedRank);
    }

    public boolean hasSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
