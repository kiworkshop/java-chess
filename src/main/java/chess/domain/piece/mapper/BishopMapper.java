package chess.domain.piece.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;

public class BishopMapper extends PieceMapper {

    private static final String NAME = "b";
    private static final double SCORE = 3;

    public BishopMapper() {
        super(NAME, SCORE);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Bishop;
    }
}
