package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.PawnMoveStrategy;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final String PAWN_SYMBOL = "p";
    private static final int PAWN_SCORE = 1;
    public static final double PAWN_SAME_FILE_SCORE = 0.5;

    private Pawn(Team team, Position position) {
        super(team, position, new PawnMoveStrategy(team));
    }

    public static Pawn of(Team team, Position position) {
        return new Pawn(team, position);
    }

    @Override
    public String symbol() {
        return PAWN_SYMBOL;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }
}
