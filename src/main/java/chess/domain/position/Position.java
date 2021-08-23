package chess.domain.position;

import java.util.*;

public class Position {
    private static final Map<String, Position> POSITION_CACHE = init();

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static Map<String, Position> init() {
        Map<String, Position> positionMap = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Position(file, rank))
                ).forEach(position -> positionMap.put(key(position.file, position.rank), position));
        return positionMap;
    }

    private static String key(File file, Rank rank) {
        return file.symbol() + rank.number();
    }

    public static Collection<Position> all() {
        return POSITION_CACHE.values();
    }

    public static Position from(String position) {
        Optional<Position> foundPosition = Optional.ofNullable(POSITION_CACHE.get(position));
        return foundPosition.orElseThrow(() -> new IllegalArgumentException("체스 판의 위치를 다시 확인해 주세요."));
    }

    public String fileSymbol() {
        return file.symbol();
    }

    public int fileNumber() {
        return file.number();
    }

    public int rankNumber() {
        return rank.number();
    }
}
