package mychess.controller.dto;

import mychess.domain.Board;

public class ChessResponse {
    private Board board;
    private String message;

    public ChessResponse(Board board, String message) {
        this.board = board;
        this.message = message;
    }

    public Board getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }
}
