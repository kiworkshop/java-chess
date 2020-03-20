package mychess.controller;

import mychess.controller.dto.ChessRequest;
import mychess.controller.dto.ChessResponse;
import mychess.controller.dto.MoveParams;
import mychess.service.ChessService;
import mychess.support.ChessMessageQueue;

import java.util.concurrent.BlockingQueue;

// 바로 체스 컨트롤러가 나와서 그렇지, 사실은 더 높은 준위에 있고, dispatcher 역할
public class ChessController implements Runnable {
    private BlockingQueue<ChessRequest> requestQueue;
    private BlockingQueue<ChessResponse> responseQueue;
    private ChessService chessService = new ChessService();

    public ChessController(ChessMessageQueue messageQueue) {
        this.requestQueue = messageQueue.getRequestQueue();
        this.responseQueue = messageQueue.getResponseQueue();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ChessRequest request = requestQueue.take();
                ChessResponse response = runCommand(request);
                responseQueue.put(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ChessResponse runCommand(ChessRequest chessRequest) {
        Command command = chessRequest.getCommand();
        switch (command) {
            case START:
                return start();
            case END:
                return end();
            case MOVE:
                return move(chessRequest);
            default:
                return new ChessResponse(null, "몰라여");
        }
    }

    public ChessResponse start() {
        return chessService.start();
    }

    public ChessResponse end() {
        return chessService.end();
    }

    public ChessResponse move(ChessRequest chessRequest) {
        return chessService.move(MoveParams.of(chessRequest.getParameters()));
    }
}

