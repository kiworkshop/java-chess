package chess.controller;

import chess.domain.piece.Piece;
import chess.domain.view.OutputView;
import chess.dto.ChessGameDto;
import chess.service.ChessGameService;

public class ChessGameController {

    ChessGameService chessGameService = new ChessGameService();

    public void gameStart() {
        ChessGameDto chessGameDto = chessGameService.startGame();
        OutputView.printChessBoard(chessGameDto);
    }

    public void movePiece(Piece source, Piece target) {
        ChessGameDto chessGameDto = chessGameService.movePiece(source, target);
        OutputView.printChessBoard(chessGameDto);
    }

    public void gameEnd() {
        chessGameService.endGame();
    }
}
