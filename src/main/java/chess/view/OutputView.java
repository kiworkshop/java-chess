package chess.view;

import chess.domain.board.Scores;
import chess.dto.console.BoardConsoleDto;

public interface OutputView {
    void printGuide();

    void printBoard(BoardConsoleDto boardConsoleDto);

    void printScores(Scores scores);

    void printTurn(String currentTurn);

    void printWinner(String winner);

    void printMessage(String message);
}
