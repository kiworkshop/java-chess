package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class BoardDto {
    public Map<Position, Piece> board;

    public BoardDto(Board board) {
        this.board = board.getBoard();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
