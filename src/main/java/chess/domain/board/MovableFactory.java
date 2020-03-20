package chess.domain.board;

import chess.domain.piece.Movable;

import java.util.HashMap;
import java.util.Map;

public class MovableFactory {

    public static Map<Position, Movable> initializeBoard() {
        Map<Position, Movable> positionPawnMap = new HashMap<>();
        for (Position position : Position.positionMap.values()) {
            Movable movable = ChessPieces.getMovable(position);
            positionPawnMap.put(position, movable);
        }

        return positionPawnMap;
    }
}
