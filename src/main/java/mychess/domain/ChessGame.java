package mychess.domain;

import mychess.controller.dto.MoveParams;

public class ChessGame {

    private Players players;
    private Board board;

    public void initialize() {
        this.players = new Players(new Player(Color.WHITE), new Player(Color.BLACK));
        this.board = new Board();
    }

    public void move(MoveParams params) {
        board.movePiece(params);
        players.changeCurrentPlayer();
    }

    private void end() {
        System.exit(0);
    }

    public Players getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
