package chess.controller;

import chess.controller.dto.ChessRequest;
import chess.controller.dto.ChessResponse;
import chess.controller.dto.MoveParams;
import chess.service.ChessService;
import chess.support.ChessMessageQueue;

import java.util.List;
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
                return move(chessRequest.getParameters());
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

    public ChessResponse move(List<String> parameters) {
        try {
            MoveParams moveParams = MoveParams.of(parameters);
            chessService.move(moveParams);
        } catch (IllegalArgumentException ie) {
            return new ChessResponse(null, ie.getMessage());
        }
//        String board = chessService.parseBoardToString();
        return new ChessResponse(null, "이동");
    }

}
