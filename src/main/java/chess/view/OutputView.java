package chess.view;

import chess.controller.dto.BoardDto;

public interface OutputView {
    void printBoard(BoardDto boardDto);
}
