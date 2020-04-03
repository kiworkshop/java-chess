package mychess.service;

import mychess.controller.dto.ChessResponse;
import mychess.domain.ChessGame;
import mychess.domain.position.Position;

public class ChessService {

    private ChessGame chessGame = new ChessGame();

    public ChessResponse start() {
        chessGame.initialize();
        return new ChessResponse(chessGame, "시스템: 게임 시작");
    }

    public ChessResponse end() {
        chessGame.end();
        return new ChessResponse(null, "시스템: 게임 종료");
    }

    public ChessResponse move(Position source, Position destination) {
        try {
            chessGame.move(source, destination);
            return new ChessResponse(chessGame, "시스템: 이동 완료");
        } catch (IllegalArgumentException e) {
            return new ChessResponse(chessGame, "시스템: 잘못된 위치입니다.");
        }
    }
}
