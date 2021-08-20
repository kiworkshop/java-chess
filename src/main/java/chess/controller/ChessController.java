package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
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
                Command command = new Command(inputView.getCommand());
                runCommand(chessGame, command);
                printBoard(chessGame);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void runCommand(ChessGame chessGame, Command command) {
        try {
            chessGame.run(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printBoard(ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }
}
