package chess.domain.piece.mapper;

import chess.domain.piece.King;
import chess.domain.piece.Piece;

public class KingMapper extends PieceMapper {

    private static final String NAME = "k";

    public KingMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof King;
    }
}
