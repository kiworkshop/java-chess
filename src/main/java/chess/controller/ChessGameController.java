package chess.controller;

import chess.domain.position.Position;
import chess.domain.view.OutputView;
import chess.dto.ChessGameDto;
import chess.service.ChessGameService;

public class ChessGameController {
    private final ChessGameService chessGameService;

    public ChessGameController() {
        this.chessGameService = new ChessGameService();
    }

    public void gameStart() {
        ChessGameDto chessGameDto = chessGameService.startGame();
        OutputView.printChessBoard(chessGameDto);
    }

    public void movePiece(String source, String target) {
        ChessGameDto chessGameDto = chessGameService.movePiece(Position.from(source), Position.from(target));
        OutputView.printChessBoard(chessGameDto);
    }

    public void gameEnd() {
        chessGameService.endGame();
    }
}
