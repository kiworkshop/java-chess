package chess.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<String, Position> POSITIONS = createPositions();

    private static Map<String, Position> createPositions() {
        Map<String, Position> positions = new HashMap<>();

        Arrays.stream(File.values())
                .forEach(file -> Arrays.stream(Rank.values()).forEach(rank -> positions.put(createKey(file, rank), new Position(file, rank))));

        return positions;
    }

    private static String createKey(final File file, final Rank rank) {
        return file.name() + rank.getRank();
    }

    public static Position from(final String key) {
        return POSITIONS.get(key);
    }

    public static Position from(final File file, final Rank rank) {
        return POSITIONS.get(createKey(file, rank));
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }
}
