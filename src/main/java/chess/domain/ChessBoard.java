package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<String, Position> positions;

    public ChessBoard() {
        this.positions = new HashMap<>();
        init();
    }

    private void init() {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Position position = new Position(file, rank);
                positions.put(key(position), position);
            }
        }
    }

    private String key(Position position) {
        return position.getFile() + position.getRank();
    }

    public Position getPositions(String key) {
        return positions.get(key);
    }

    public int size() {
        return positions.size();
    }
}
