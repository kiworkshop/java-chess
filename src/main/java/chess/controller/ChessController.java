package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
import chess.domain.board.Status;
import chess.domain.command.CommandOptions;
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
        ChessGame chessGame = initialize();

        while (chessGame.isRunning()) {
            play(chessGame);
        }

        printResult(chessGame);
    }

    private ChessGame initialize() {
        outputView.printGuide();
        ChessGame chessGame = new ChessGame();
        CommandOptions initialCommandOptions = CommandOptions.of(inputView.getCommand());
        executeInitialCommand(initialCommandOptions, chessGame);

        return chessGame;
    }

    private void executeInitialCommand(final CommandOptions commandOptions, final ChessGame chessGame) {
        commandOptions.validateInitialCommand();

        if (commandOptions.isEnd()) {
            chessGame.end();
        }
    }

    private void play(final ChessGame chessGame) {
        try {
            outputView.printTurn(chessGame.isWhiteTurn());
            CommandOptions commandOptions = CommandOptions.of(inputView.getCommand());
            executeCommand(chessGame, commandOptions);
            printBoard(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executeCommand(final ChessGame chessGame, final CommandOptions commandOptions) {
        if (commandOptions.isEnd()) {
            chessGame.end();
            return;
        }

        if (commandOptions.isMove()) {
            chessGame.move(commandOptions.getMoveOptions());
            return;
        }

        if (commandOptions.isStatus()) {
            printStatus(chessGame);
        }
    }

    private void printBoard(final ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }

    private void printStatus(final ChessGame chessGame) {
        Status status = chessGame.getStatus();
        outputView.printStatus(status);
    }

    private void printResult(final ChessGame chessGame) {
        printBoard(chessGame);
        printStatus(chessGame);
    }
}
