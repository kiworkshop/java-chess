package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Movable;

import java.util.Map;

public class Board {


    private Map<Position, Movable> positionPawnMap;

    public Board() {
        positionPawnMap = MovableFactory.initializeBoard();
    }

    public Movable getPawn(String position) {
        return positionPawnMap.get(Position.from(position));
    }

    public void move(String prev, String next) {
        Position currentPosition = Position.from(prev);
        Position nextPosition = Position.from(next);

        Movable currentPiece = positionPawnMap.get(currentPosition);
        if (!currentPiece.canMove(currentPosition, nextPosition)) {
            throw new IllegalArgumentException(currentPiece.getMovingPolicy());
        }
        positionPawnMap.replace(nextPosition, currentPiece);
        positionPawnMap.replace(currentPosition, new Empty());
    }
}
