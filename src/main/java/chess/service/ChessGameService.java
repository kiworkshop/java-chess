package chess.service;

import chess.domain.piece.Piece;
import chess.dto.ChessGameDto;
import chess.game.ChessGame;

public class ChessGameService {
    private ChessGame chessGame;

    public ChessGameService() {
        this.chessGame = chessGame.ready();
    }

    public ChessGameDto startGame() {
        return new ChessGameDto(chessGame.start());
    }

    public ChessGameDto movePiece(Piece source, Piece target) {
        ChessGame move = chessGame.move(source, target);
        return new ChessGameDto(move);
    }

    public ChessGameDto endGame() {
        return new ChessGameDto(chessGame.end());
    }
}
