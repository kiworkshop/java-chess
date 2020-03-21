package chess.service;

import chess.controller.dto.ChessResponse;
import chess.domain.ChessGame;
import chess.domain.position.Position;

public class ChessService {

    private ChessGame chessGame;

    public ChessResponse start() {
        chessGame = new ChessGame();
        return new ChessResponse(null, "게임 시작");
    }

    public ChessResponse end() {
        chessGame.end();
        return new ChessResponse(null, "게임 종료");
    }

    public void move(Position source, Position destination) {
        ChessGame.move(source, destination);
    }
}
