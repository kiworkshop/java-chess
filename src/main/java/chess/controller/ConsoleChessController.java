package chess.controller;

import chess.domain.board.Scores;
import chess.domain.command.Command;
import chess.domain.command.MoveParameters;
import chess.exception.ForcedTerminationException;
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
                outputView.printTurn(chessService.getCurrentTurnView());
                Command command = new Command(inputView.getCommand());
                run(command);
                printBoard();
            } catch (UnsupportedOperationException e) {
                outputView.printMessage(e.getMessage());
            } catch (ForcedTerminationException e) {
                outputView.printMessage(e.getMessage());
                break;
            }
        }

        printFinalResult();
    }

    private void run(final Command command) {
        try {
            if (command.isStart()) {
                return;
            }

            if (command.isEnd()) {
                throw new ForcedTerminationException();
            }

            if (command.isMove()) {
                MoveParameters parameters = command.getMoveParameters();
                chessService.movePiece(parameters);
                return;
            }

            if (command.isStatus()) {
                printScores();
                return;
            }
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
            return;
        }

        throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
    }

    private void printBoard() {
        Map<String, String> boardDto = chessService.getBoardDto();
        outputView.printBoard(boardDto);
    }

    private void printScores() {
        Scores scores = chessService.getScores();
        outputView.printScores(scores);
    }

    private void printWinner() {
        try {
            outputView.printWinner(chessService.getWinnerView());
        } catch (IllegalStateException e) {
            outputView.printMessage(e.getMessage());
        }
    }

    private void printFinalResult() {
        printBoard();
        printScores();
        printWinner();
    }
}
