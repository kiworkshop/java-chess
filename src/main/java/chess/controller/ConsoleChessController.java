package chess.controller;

import chess.domain.board.Status;
import chess.domain.command.Command;
import chess.domain.command.MoveParameters;
import chess.dto.console.BoardConsoleDto;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

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
                outputView.printTurn(chessService.getCurrentTurn());
                Command command = new Command(inputView.getCommand());
                run(command);
                printBoard();
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        printBoard();
        printStatus();
    }

    private void run(final Command command) {
        try {
            if (command.isStart()) {
                return;
            }

            if (command.isEnd()) {
                chessService.finish();
                return;
            }

            if (command.isMove()) {
                MoveParameters parameters = command.getMoveParameters();
                chessService.movePiece(parameters);
                return;
            }

            if (command.isStatus()) {
                printStatus();
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        throw new UnsupportedOperationException("유효하지 않은 명령어입니다.");
    }

    private void printBoard() {
        BoardConsoleDto boardConsoleDto = chessService.getBoardConsoleView();
        outputView.printBoard(boardConsoleDto);
    }

    private void printStatus() {
        Status status = chessService.getStatus();
        outputView.printStatus(status);
    }
}
