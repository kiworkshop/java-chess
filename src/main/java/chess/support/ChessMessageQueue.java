package chess.support;

import chess.controller.dto.ChessRequest;
import chess.controller.dto.ChessResponse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ChessMessageQueue {
    private BlockingQueue<ChessRequest> requestQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<ChessResponse> responseQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<ChessRequest> getRequestQueue() {
        return requestQueue;
    }

    public BlockingQueue<ChessResponse> getResponseQueue() {
        return responseQueue;
    }
}
