package chess.domain.position;

import chess.domain.movement.Direction;

import java.util.*;

public class Position {
    private static final Map<String, Position> positions = new LinkedHashMap<>();
    private static final int MIN_MOVE_POSITION = 1;
    private static final int MAX_MOVE_POSITION = 8;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static String key(File file, Rank rank) {
        return file.getSymbol() + rank.getNumber();
    }

    public static Map<String, Position> init() {
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Position(file, rank))
                ).forEach(position -> positions.put(key(position.file, position.rank), position));
        return positions;
    }

    public static Position from(String position) {
        Optional<Position> foundPosition = Optional.ofNullable(positions.get(position));
        return foundPosition.orElseThrow(() -> new IllegalArgumentException("체스 판의 위치를 다시 확인해 주세요."));
    }

    public static Collection<Position> all() {
        return positions.values();
    }

    public Optional<Position> findBy(Direction direction) {
        int fileDirection = direction.findFileNumber(file.getNumber(), 1);
        int rankDirection = direction.findRankNumber(rank.getNumber(), 1);

        if (isValidMovePosition(fileDirection, rankDirection)) {
            String key = key(File.findBy(fileDirection), Rank.findBy(rankDirection));
            return Optional.of(from(key));
        }
        return Optional.empty();
    }

    public Optional<Position> findBy(Direction direction, int repeatCount) {
        int fileDirection = direction.findFileNumber(file.getNumber(), repeatCount);
        int rankDirection = direction.findRankNumber(rank.getNumber(), repeatCount);

        if (isValidMovePosition(fileDirection, rankDirection)) {
            String key = key(File.findBy(fileDirection), Rank.findBy(rankDirection));
            return Optional.of(from(key));
        }
        return Optional.empty();
    }

    private boolean isValidMovePosition(int fileDirection, int rankDirection) {
        return (fileDirection >= MIN_MOVE_POSITION && fileDirection <= MAX_MOVE_POSITION)
                && (rankDirection >= MIN_MOVE_POSITION && rankDirection <= MAX_MOVE_POSITION);
    }

    public String getFileSymbol() {
        return file.getSymbol();
    }

    public int getFileNumber() {
        return file.getNumber();
    }

    public int getRankNumber() {
        return rank.getNumber();
    }
}
