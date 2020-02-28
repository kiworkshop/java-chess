package mychess.domain;

import java.util.EnumMap;
import java.util.Map;

public class Board {

    private Map<Position, Piece> pieces;

    public Board() {
        initialize();
    }

    private void initialize() {
        pieces = new EnumMap<>(Position.class);

        for (Position p : Position.values()) {
            pieces.put(p, new Piece());
        }
    }
}
