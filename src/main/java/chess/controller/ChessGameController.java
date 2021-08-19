package chess.controller;

import chess.domain.view.OutputView;
import chess.dto.ChessGameDto;
import chess.game.ChessGame;

public class ChessGameController {

    private final ChessGame chessGame = new ChessGame();

    public void gameStart() {
        ChessGameDto chessGameDto = chessGame.startGame();
        OutputView.printChessBoard(chessGameDto);
    }

    public void gameEnd() {
        chessGame.endGame();
    }
}
