package mychess.service;

import mychess.controller.dto.ChessResponse;
import mychess.controller.dto.MoveParams;
import mychess.domain.ChessGame;
import mychess.domain.Color;
import mychess.domain.Player;

public class ChessService {

    private ChessGame chessGame = new ChessGame();

    public ChessResponse start() {
        chessGame.initialize();
        return new ChessResponse(chessGame, "게임 시작");
    }

    public ChessResponse end() {
        return new ChessResponse(null, "게임 종료");
    }

    public ChessResponse move(MoveParams params) {
        chessGame.move(params);
        return new ChessResponse(chessGame, "이동");
    }
}
