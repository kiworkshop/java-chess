package chess.domain.board;

import chess.domain.piece.type.MoveUnit;

import java.util.*;

public class Position {

    private static final Map<String, Position> POSITIONS = Collections.unmodifiableMap(createPositions());

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

    public static Position of(String key) {
        key = key.toLowerCase();

        if (!POSITIONS.containsKey(key)) {
            throw new IllegalArgumentException("위치 입력값이 잘못되었습니다.");
        }

        return POSITIONS.get(key);
    }

    public static Position from(final File file, final Rank rank) {
        return of(createKey(file, rank));
    }

    public static Collection<String> names() {
        return POSITIONS.keySet();
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

    public Set<Position> findPassingPositions(Position target, MoveUnit moveUnit) {
        Set<Position> positions = new HashSet<>();
        Position current = this;

        while (!target.equals(current)) {
            current = current.move(moveUnit);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    public Collection<Position> findAvailablePositions(final MoveUnit moveUnit, final boolean isFinite) {
        if (isFinite) {
            return getFinitePositions(moveUnit);
        }
        return getInfinitePositions(moveUnit);
    }

    private Collection<Position> getFinitePositions(MoveUnit moveUnit) {
        if (isMovable(moveUnit)) {
            return Collections.singleton(move(moveUnit));
        }
        return Collections.emptySet();
    }

    private Collection<Position> getInfinitePositions(MoveUnit moveUnit) {
        Collection<Position> positions = new HashSet<>();

        Position current = this;
        while (current.isMovable(moveUnit)) {
            current = current.move(moveUnit);
            positions.add(current);
        }

        return positions;
    }

    private boolean isMovable(final MoveUnit moveUnit) {
        return rank.canMove(moveUnit.getRank()) && file.canMove(moveUnit.getFile());
    }

    private Position move(final MoveUnit moveUnit) {
        File file = this.file.add(moveUnit.getFile());
        Rank rank = this.rank.add(moveUnit.getRank());
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
