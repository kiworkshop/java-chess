package chess.domain;

import chess.controller.dto.MoveParams;
import chess.domain.board.ChessBoard;

public class ChessGame {

    private ChessBoard chessBoard;
    private boolean turn = true;

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void move(MoveParams moveParams) {
        chessBoard.move(moveParams.getSource(), moveParams.getDestination());
    }
//
//    private player1
//    private player2
//    private turn
//    private startAt
//    private endAt
}
