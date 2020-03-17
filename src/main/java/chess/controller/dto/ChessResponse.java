package chess.controller.dto;

public class ChessResponse {
    private String board;
    private String message;

    public ChessResponse(String board, String message) {
        this.board = board;
        this.message = message;
    }

    public String getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }
}
