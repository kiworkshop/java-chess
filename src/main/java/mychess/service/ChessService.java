package mychess.service;

import mychess.controller.dto.ChessResponse;
import mychess.controller.dto.MoveParams;
import mychess.domain.ChessGame;
import mychess.domain.Player;

public class ChessService {

    private ChessGame chessGame = new ChessGame(new Player(true), new Player(false));

    public ChessResponse start() {
        chessGame.createBoard();
        return new ChessResponse(chessGame.getBoard(), "게임 시작");
    }

    public ChessResponse end() {
        return new ChessResponse(null, "게임 종료");
    }

    public ChessResponse move(MoveParams params) {
        chessGame.move(params);
        return new ChessResponse(chessGame.getBoard(), "이동");
    }
}
