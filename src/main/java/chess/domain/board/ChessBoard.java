package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Movable;

import java.util.Map;

public class ChessBoard {

    private Map<Position, Movable> positionMovableMap;

    public ChessBoard() {
        positionMovableMap = MovableFactory.initializeBoard();
    }

    public Movable getMovable(String position) {
        return positionMovableMap.get(Position.from(position));
    }

    public void move(Position source, Position destination) {
        Movable currentPiece = positionMovableMap.get(source);
        if (!currentPiece.canMove(source, destination)) {
            throw new IllegalArgumentException(currentPiece.getMovingPolicy());
        }
        positionMovableMap.replace(destination, currentPiece);
        positionMovableMap.replace(source, Empty.create());
    }
}
