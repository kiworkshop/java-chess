package chess.domain.piece;

import chess.domain.board.Position;

public class Empty implements Movable {

    private static final String MOVING_POLICY = "비어있는 체스판입니다";
    private static Movable empty = new Empty();

    private Empty() {
    }

    public static Movable create() {
        return empty;
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        return false;
    }

    @Override
    public String getMovingPolicy() {
        return MOVING_POLICY;
    }

    @Override
    public String toString() {
        return "[ ]";
    }
}
