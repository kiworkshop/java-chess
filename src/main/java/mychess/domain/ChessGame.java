package mychess.domain;

import mychess.domain.command.AbstractCommand;

public class ChessGame {

    private final Player playerWhite;
    private final Player playerBlack;
    private Board board;

    public ChessGame(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public void processCommand(AbstractCommand command) {
        if (command.isEndCommand()) end();
        if (command.isStartCommand()) createBoard();
    }

    private void end() {
        System.exit(0);
    }

    private void createBoard() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }
}
