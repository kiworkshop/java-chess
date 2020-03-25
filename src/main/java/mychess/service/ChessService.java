package mychess.service;

import mychess.controller.dto.ChessResponse;
import mychess.domain.ChessGame;
import mychess.domain.position.Position;

public class ChessService {

    private ChessGame chessGame = new ChessGame();

    public ChessResponse start() {
        chessGame.initialize();
        return new ChessResponse(chessGame, "게임 시작");
    }

    public ChessResponse end() {
        return new ChessResponse(null, "게임 종료");
    }

    public void move(Position source, Position destination) {
        chessGame.move(source, destination);
    }
}
