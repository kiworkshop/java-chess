package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> POSITION_CACHE = init();

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static Map<String, Position> init() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank))
                ).collect(Collectors.toMap(
                        Position::key, position -> position
                ));
    }

    private static String key(Position position) {
        return position.getFile() + position.getRank();
    }

    public static Position of(String position) {
        return POSITION_CACHE.get(position);
    }

    public String getFile() {
        return file.value();
    }

    public int getRank() {
        return rank.value();
    }
}
