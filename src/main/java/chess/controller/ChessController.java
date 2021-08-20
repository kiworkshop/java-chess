package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
import chess.domain.board.Status;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGuide();
        ChessGame chessGame = new ChessGame();

        while (chessGame.isRunning()) {
            try {
                outputView.printTurn(chessGame.isWhiteTurn());
                Command command = new Command(inputView.getCommand());
                runCommand(chessGame, command);
                printBoard(chessGame);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        printBoard(chessGame);
        printStatus(chessGame);
    }

    private void runCommand(final ChessGame chessGame, final Command command) {
        try {
            if (command.isStart()) {
                return;
            }

            if (command.isEnd()) {
                chessGame.end();
                return;
            }

            if (command.isMove()) {
                chessGame.move(command.getMoveParameters());
                return;
            }

            if (command.isStatus()) {
                printStatus(chessGame);
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
    }

    private void printBoard(final ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }

    private void printStatus(final ChessGame chessGame) {
        Status status = chessGame.getStatus();
        outputView.printStatus(status);
    }
}
