package chess.service;

import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.game.ChessGame;

public class ChessGameService {
    private final ChessGame chessGame;

    public ChessGameService() {
        this.chessGame = ChessGame.ready();
    }

    public ChessGameDto startGame() {
        return new ChessGameDto(chessGame.start());
    }

    public ChessGameDto movePiece(Position source, Position target) {
        ChessGame move = chessGame.move(source, target);
        return new ChessGameDto(move);
    }

    public ChessGameDto endGame() {
        return new ChessGameDto(chessGame.end());
    }
}
