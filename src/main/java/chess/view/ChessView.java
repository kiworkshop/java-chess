package chess.view;

import chess.controller.dto.ChessRequest;
import chess.controller.dto.ChessResponse;
import chess.support.ChessMessageQueue;

import java.util.concurrent.BlockingQueue;

public abstract class ChessView implements Runnable {
    protected ChessInput chessInput;
    protected ChessOutput chessOutput;
    private BlockingQueue<ChessRequest> requestQueue;
    private BlockingQueue<ChessResponse> responseQueue;

    public ChessView(ChessInput chessInput, ChessOutput chessOutput, ChessMessageQueue messageQueue) {
        this.chessInput = chessInput;
        this.chessOutput = chessOutput;
        this.requestQueue = messageQueue.getRequestQueue();
        this.responseQueue = messageQueue.getResponseQueue();
    }

    @Override
    public void run() {
        while (true) {
            try {
                requestQueue.put(chessInput.read());
                chessOutput.write(responseQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
