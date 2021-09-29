package chess.view;

import chess.dto.Scores;
import chess.dto.TurnDto;

import java.util.Map;

public interface OutputView {
    void printGuide();

    void printBoard(Map<String, String> boardDto);

    void printScores(Scores scores);

    void printTurn(TurnDto currentTurn);

    void printWinner(String winner);

    void printMessage(String message);
}
