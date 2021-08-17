package chess.domain.piece.mapper;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;

public class RookMapper extends PieceMapper {

    private static final String NAME = "r";

    public RookMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Rook;
    }
}
