package chess.domain.piece.mapper;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class PawnMapper extends PieceMapper {

    private static final String NAME = "p";
    private static final int DUPLICATION_THRESHOLD = 1;
    private static final double SCORE = 1;
    private static final double SCORE_ON_DUPLICATION = 0.5;

    public PawnMapper() {
        super(NAME, SCORE);
    }

    public static double calculate(final int count) {
        if (count > DUPLICATION_THRESHOLD) {
            return count * SCORE_ON_DUPLICATION;
        }

        return SCORE;
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Pawn;
    }
}
