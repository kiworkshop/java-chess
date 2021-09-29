package chess.controller;

import chess.domain.command.Command;
import chess.dto.Scores;
import chess.dto.TurnDto;
import chess.exception.ForcedTerminationException;
import chess.exception.ScoresRequestedException;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ConsoleChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;

    public ConsoleChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessService = new ChessService();
    }

    public void run() {
        outputView.printGuide();

        while (chessService.isGameRunning()) {
            try {
                runOneTurn();
                printBoard();
            } catch (ForcedTerminationException e) {
                outputView.printMessage(e.getMessage());
                break;
            }
        }

        printFinalResult();
    }

    private void runOneTurn() {
        try {
            TurnDto currentTurn = chessService.getCurrentTurnDto();
            outputView.printTurn(currentTurn);

            Command command = new Command(inputView.getCommand());
            chessService.run(command);
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
        } catch (ScoresRequestedException e) {
            Scores scores = chessService.getScores();
            outputView.printScores(scores);
        }
    }

    private void printFinalResult() {
        printBoard();
        printWinner();
    }

    private void printBoard() {
        Map<String, String> boardDto = chessService.getBoardDto();
        outputView.printBoard(boardDto);
    }

    private void printWinner() {
        try {
            String winner = chessService.getWinnerDto();
            outputView.printWinner(winner);
        } catch (IllegalStateException e) {
            outputView.printMessage(e.getMessage());
        }
    }
}
