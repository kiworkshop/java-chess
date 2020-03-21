package chess.domain.board;

import chess.domain.piece.PieceDto;
import chess.domain.position.Position;

import java.util.Map;

public class ChessBoardDto {
    private Map<Position, PieceDto> pieces;
}
