package chess.domain.piece.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;

public class BishopMapper extends PieceMapper {

    private static final String NAME = "b";

    public BishopMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Bishop;
    }
}
