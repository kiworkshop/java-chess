package chess.controller;

import chess.domain.view.OutputView;
import chess.dto.BoardDto;
import chess.service.ChessGameService;

public class ChessGameController {

    public void gameStart() {
        ChessGameService chessGameService = new ChessGameService();
        BoardDto boardDto = chessGameService.start();
        OutputView.printChessBoard(boardDto);
    }
}
