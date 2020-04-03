package mychess.domain;

import mychess.domain.board.Board;
import mychess.domain.player.Player;
import mychess.domain.player.Players;
import mychess.domain.position.Position;

public class ChessGame {

    private Players players;
    private Board board;
    private int turnCount;

    public void initialize() {
        this.players = new Players(Player.WHITE, Player.BLACK);
        this.board = new Board();
    }

    public void move(Position source, Position destination) throws IllegalArgumentException {
        board.move(source, destination);
        players.changeCurrentPlayer();
    }

    public void end() {
        System.exit(0);
    }

    public Players getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
