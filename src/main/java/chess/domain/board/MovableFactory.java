package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Movable;
import chess.domain.piece.Pawn;

import java.util.HashMap;
import java.util.Map;

public class MovableFactory {

    public static Map<Position, Movable> initializeBoard() {
        Map<Position, Movable> positionPawnMap = new HashMap<>();
        for (Position position : Position.positionMap.values()) {
            Movable movable = checkStartingPoint(position);
            positionPawnMap.put(position, movable);
        }

        return positionPawnMap;
    }

    private static Movable checkStartingPoint(Position position) {
        if (!position.isStartingPosition()) {
            return new Empty();
        }
        return checkPawn(position);
    }

    private static Movable checkPawn(Position position) {
        if (position.isWhiteStartingPosition()) {
            return new Pawn(true);
        }
        return new Pawn(false);
    }
}
