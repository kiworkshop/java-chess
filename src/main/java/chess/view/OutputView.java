package chess.view;

import chess.controller.dto.BoardDto;

public interface OutputView {
    void printGuide();

    void printBoard(BoardDto boardDto);
}
