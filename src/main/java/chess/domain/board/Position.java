package chess.domain.board;

import chess.domain.piece.move.Gap;
import chess.domain.piece.move.MoveUnit;

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

    public static Collection<String> names() {
        return POSITIONS.keySet();
    }

    public static Position from(final File file, final Rank rank) {
        return of(createKey(file, rank));
    }

    public static Position of(String key) {
        key = key.toLowerCase();

        if (!POSITIONS.containsKey(key)) {
            throw new IllegalArgumentException("위치 입력값이 잘못되었습니다.");
        }

        return POSITIONS.get(key);
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Gap calculateGap(final Position position) {
        int fileGap = calculateFileGap(position);
        int rankGap = calculateRankGap(position);

        return new Gap(fileGap, rankGap);
    }

    private int calculateFileGap(final Position position) {
        return file.calculateGap(position.getFile());
    }

    private int calculateRankGap(final Position position) {
        return rank.calculateGap(position.getRank());
    }

    public List<Position> findPassingPositions(final Position target, final MoveUnit moveUnit) {
        List<Position> positions = new ArrayList<>();

        Position current = this;
        while (!target.equals(current)) {
            current = current.move(moveUnit);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    public List<Position> findReachablePositions(final MoveUnit moveUnit) {
        List<Position> positions = new ArrayList<>();

        Position current = this;
        while (current.isMovable(moveUnit)) {
            current = current.move(moveUnit);
            positions.add(current);
        }

        return positions;
    }

    public boolean isMovable(final MoveUnit moveUnit) {
        return rank.canMove(moveUnit.getRank()) && file.canMove(moveUnit.getFile());
    }

    public Position move(final MoveUnit moveUnit) {
        File nextFile = this.file.add(moveUnit.getFile());
        Rank nextRank = this.rank.add(moveUnit.getRank());

        return Position.from(nextFile, nextRank);
    }

    public boolean isSame(Position position) {
        return this == position;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isWhitePawnInitialRank() {
        return this.rank.isWhiteInitialRank();
    }

    public boolean isBlackPawnInitialRank() {
        return this.rank.isBlackInitialRank();
    }
}
