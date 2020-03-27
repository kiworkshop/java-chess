package chess.domain;

import chess.controller.dto.MoveParams;
import chess.domain.board.ChessBoard;
import chess.domain.player.EnumTeam;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardInitializer;

public class ChessGame {

    private Board chessBoard;
    private Turn turn = new TurnImpl(EnumTeam.WHITE);

    public ChessGame(BoardInitializer boardInitializer) {
        chessBoard = ChessBoard.of(boardInitializer);
    }

    public Board getChessBoard() {
        return chessBoard;
    }

    public void move(MoveParams moveParams) {
        chessBoard.move(moveParams.getSource(), moveParams.getDestination(), turn);
    }
}
