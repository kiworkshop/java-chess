package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.dto.BoardDto;

public class ChessGameService {
    public BoardDto start() {
        Board board = Board.of(BoardInitializer.reset());
        return new BoardDto(board);
    }
}
