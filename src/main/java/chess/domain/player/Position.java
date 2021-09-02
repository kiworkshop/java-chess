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
        Position position = POSITIONS.get(key.toUpperCase());

        if (position == null) {
            throw new IllegalArgumentException("해당 파일과 랭크에 대한 위치가 존재하지 않습니다.");
        }

        return position;
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

    public Set<Position> findPassingPositions(final Position target, final Direction direction) {
        Set<Position> positions = new HashSet<>();
        Position current = this;

        while (target.isDifferent(current)) {
            current = current.move(direction);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    private boolean isDifferent(final Position current) {
        return !this.equals(current);
    }

    public Collection<Position> findAvailablePositions(final Direction direction, final boolean canMoveInfinitely) {
        if (canMoveInfinitely) {
            return findInfinitePositions(direction);
        }

        return findFinitePositions(direction);
    }

    private Collection<Position> findInfinitePositions(final Direction direction) {
        Collection<Position> positions = new HashSet<>();
        Position current = this;

        while (current.isMovable(direction)) {
            current = current.move(direction);
            positions.add(current);
        }

        return positions;
    }

    private Collection<Position> findFinitePositions(final Direction direction) {
        if (isMovable(direction)) {
            return Collections.singleton(move(direction));
        }

        return Collections.emptySet();
    }

    private boolean isMovable(final Direction direction) {
        return rank.canMove(direction.getY()) && file.canMove(direction.getX());
    }

    private Position move(final Direction direction) {
        File movedFile = this.file.move(direction.getX());
        Rank movedRank = this.rank.move(direction.getY());
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
