package chess.domain.piece.mapper;

import chess.domain.piece.King;
import chess.domain.piece.Piece;

public class KingMapper extends PieceMapper {

    private static final String NAME = "k";
    private static final double SCORE = 0;

    public KingMapper() {
        super(NAME, SCORE);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof King;
    }
}
