package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.BishopMoveStrategy;
import chess.domain.position.Position;

public class Bishop extends Piece {
    private static final String BISHOP_SYMBOL = "b";
    private static final int BISHOP_SCORE = 3;

    private Bishop(Team team, Position position) {
        super(team, position, new BishopMoveStrategy());
    }

    public static Bishop of(Team team, Position position) {
        return new Bishop(team, position);
    }

    @Override
    public String symbol() {
        return BISHOP_SYMBOL;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }
}

