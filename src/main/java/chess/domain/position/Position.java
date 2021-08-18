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
                ).forEach(position -> positionMap.put(key(position), position));
        return positionMap;
    }

    private static String key(Position position) {
        return position.getFileName() + position.getRankName();
    }

    public static Collection<Position> all() {
        return POSITION_CACHE.values();
    }

    public static Position from(String position) {
        return POSITION_CACHE.get(position);
    }

    public String getFileName() {
        return file.value();
    }

    public int getFileNumber() {
        return file.number();
    }

    public int getRankName() {
        return rank.value();
    }
}
