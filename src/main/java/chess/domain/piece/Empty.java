package chess.domain.piece;

import chess.domain.board.Position;

public class Empty implements Movable {

    public static final String MOVING_POLICY = "비어있는 체스판입니다";

    @Override
    public boolean canMove(Position prev, Position next) {
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
