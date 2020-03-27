package mychess.domain;

import mychess.domain.player.Player;
import mychess.domain.player.Players;
import mychess.domain.position.Position;

public class ChessGame {

    private Players players;
    private Board board;

    public void initialize() {
        this.players = new Players(new Player(Color.WHITE), new Player(Color.BLACK));
        this.board = new Board();
    }

    public void move(Position source, Position destination) throws IllegalArgumentException {
        board.movePiece(players.getCurrentPlayer(), source, destination);
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
