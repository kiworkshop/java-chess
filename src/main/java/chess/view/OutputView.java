package chess.view;

import chess.domain.player.Scores;

import java.util.Map;

public interface OutputView {
    void printGuide();

    void printBoard(Map<String, String> boardDto);

    void printScores(Scores scores);

    void printTurn(String currentTurn);

    void printWinner(String winner);

    void printMessage(String message);
}
