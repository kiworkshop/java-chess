package mychess.controller.dto;

import mychess.domain.Board;
import mychess.domain.ChessGame;
import mychess.domain.player.Player;

public class ChessResponse {
    private ChessGame chessGame;
    private String message;

    public ChessResponse(ChessGame ChessGame, String message) {
        this.chessGame = ChessGame;
        this.message = message;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public Board getBoard() { return chessGame.getBoard(); }

    public Player getCurrentPlayer() { return chessGame.getPlayers().getCurrentPlayer(); }

    public String getMessage() {
        return message;
    }
}
