package chess.service;

import chess.domain.position.Position;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.game.ChessGame;
import chess.game.Score;

public class ChessGameService {
    private final ChessGame chessGame;

    public ChessGameService() {
        this.chessGame = ChessGame.ready();
    }

    public boolean isReady() {
        return chessGame.gameState() instanceof Ready;
    }

    public ChessGameDto startGame() {
        return new ChessGameDto(chessGame.start());
    }

    public ChessGameDto movePiece(Position source, Position target) {
        ChessGame move = chessGame.move(source, target);
        return new ChessGameDto(move);
    }

    public boolean isPlaying() {
        return chessGame.gameState() instanceof Playing;
    }

    public ChessGameDto endGame() {
        return new ChessGameDto(chessGame.end());
    }

    public ScoreDto status() {
        Score score = chessGame.status();
        return new ScoreDto(score.white(), score.black());
    }
}
