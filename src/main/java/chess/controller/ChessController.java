package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;

    public ChessController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        Board board = new Board();
        BoardDto boardDto = new BoardDto(board);
        outputView.printBoard(boardDto);
    }
}
