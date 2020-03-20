package mychess.domain;

import mychess.controller.dto.MoveParams;

public class ChessGame {

    private Player playerWhite;
    private Player playerBlack;
    private Board board;

    public ChessGame(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public void createBoard() {
        this.board = new Board();
    }

    public void move(MoveParams params) {
        board.movePiece(params);
    }

    private void end() {
        System.exit(0);
    }

    public Board getBoard() {
        return board;
    }
}
