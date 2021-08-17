package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
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
        String initialCommand = inputView.getInitialCommand();
        ChessGame chessGame = new ChessGame(initialCommand);

        while (chessGame.isRunning()) {
            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            outputView.printBoard(boardDto);
        }
    }
}
