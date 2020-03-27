package chess.controller;

import chess.controller.dto.ChessResponse;
import chess.controller.dto.ChessRequest;
import chess.model.ChessService;
import chess.view.ChessInput;
import chess.view.ChessOutput;

import static chess.controller.dto.ChessRequest.Action.END;

public class ChessController {
    private ChessInput chessInput;
    private ChessOutput chessOutput;
    private ChessService chessService;

    private ChessController(ChessInput chessInput, ChessOutput chessOutput) {
        this.chessInput = chessInput;
        this.chessOutput = chessOutput;
        this.chessService = new ChessService();
    }
    public static ChessController of(ChessInput chessInput, ChessOutput chessOutput) {
        return new ChessController(chessInput, chessOutput);
    }

    public void run() {
        chessOutput.notifyChessStart();

        ChessRequest request;
        do {
            request = chessInput.getUserRequest();
            switch (request.getAction()) {
                case START:
                    handleChessStart();
                    break;
                case END:
                    handleChessEnd();
                    break;
                case MOVE:
                    handleChessMove(request);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + request.getAction());
            }
        } while (request.getAction() != END);
    }

    private void handleChessStart() {
        ChessResponse response = chessService.start();
        if (response.getStatus() != ChessResponse.Status.SUCCESS) {
            throw new IllegalStateException("Unexpected value: " + response.getStatus());
        }
        chessOutput.showChessBoard(response);
    }

    private void handleChessEnd() {
        ChessResponse response = chessService.end();
        if (response.getStatus() != ChessResponse.Status.SUCCESS) {
            throw new IllegalStateException("Unexpected value: " + response.getStatus());
        }
        chessOutput.notifyChessEnd();
    }

    private void handleChessMove(ChessRequest request) {
        ChessResponse response = chessService.move(request);
        chessOutput.showChessBoard(response);
    }
}
