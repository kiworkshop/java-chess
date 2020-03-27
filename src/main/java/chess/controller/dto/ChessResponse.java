package chess.controller.dto;

public class ChessResponse {
    public enum Status {
        SUCCESS,
        WHITE_WIN, BLACK_WIN,                                                   // MESSAGE from ChessService.move
        ERROR_GAME_NOT_STARTED,                                                 // ERROR from ChessService.move
        ERROR_BLACK_TURN, ERROR_WHITE_TURN,                                     // ERROR from ChessService.move
        ERROR_WRONG_MOVE, ERROR_INPUT_OUT_OF_BOUND, ERROR_SOURCE_NOT_VALID      // ERROR from ChessService.move
    };
    public enum Piece {
        BLACK_PAWN, BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_KING, BLACK_QUEEN,
        WHITE_PAWN, WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_KING, WHITE_QUEEN,
        EMPTY
    };
    public enum Turn {
        BLACK, WHITE
    };

    private Status status;
    private Piece pieces[][];
    private Turn turn;

    private ChessResponse(Status status, Piece[][] pieces, Turn turn) {
        this.status = status;
        this.pieces = pieces;
        this.turn = turn;
    }
    public static ChessResponse ofSuccess() {
        return new ChessResponse(Status.SUCCESS, null, null);
    }
    public static ChessResponse of(Status status) {
        return new ChessResponse(status, null, null);
    }
    public static ChessResponse of(Status status, Piece[][] board, Turn turn) {
        return new ChessResponse(status, board, turn);
    }

    public Status getStatus() {
        return status;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Turn getTurn() {
        return turn;
    }
}
