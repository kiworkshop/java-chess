package mychess.domain.board;

import mychess.domain.piece.PieceDto;
import mychess.domain.position.Position;

import java.util.Map;

public class BoardDto {
    private Map<Position, PieceDto> pieces;
}
