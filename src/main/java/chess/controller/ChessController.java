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
        ChessGame chessGame = new ChessGame();

        while (chessGame.isRunning()) {
            Command command = new Command(inputView.getCommand());
            chessGame.run(command);
            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            outputView.printBoard(boardDto);
        }
    }
}
