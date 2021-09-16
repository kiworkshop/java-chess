package chess.view;

import chess.controller.dto.BoardDto;
import chess.domain.board.Status;

public interface OutputView {
    void printGuide();

    void printBoard(BoardDto boardDto);

    void printStatus(Status status);

    void printTurn(boolean isWhiteTurn);
}
