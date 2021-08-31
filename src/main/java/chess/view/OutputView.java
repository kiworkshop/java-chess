package chess.view;

import chess.dto.console.BoardConsoleDto;
import chess.domain.board.Status;

public interface OutputView {
    void printGuide();

    void printBoard(BoardConsoleDto boardConsoleDto);

    void printStatus(Status status);

    void printTurn(String currentTurn);
}
