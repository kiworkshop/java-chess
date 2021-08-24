package chess.service;

import chess.domain.position.Position;
import chess.domain.state.Finish;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.game.ChessGame;
import chess.game.Score;

import java.util.List;

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
        ChessGame move = chessGame.moveAndToggleTurn(source, target);
        return new ChessGameDto(move);
    }

    public boolean isPlaying() {
        return chessGame.gameState() instanceof Playing;
    }

    public List<String> endGame() {
        return chessGame.end();
    }

    public ScoreDto status() {
        Score score = chessGame.status();
        return new ScoreDto(score.white(), score.black());
    }

    public boolean isFinish() {
        return chessGame.gameState() instanceof Finish;
    }

    public List<String> winner() {
        return chessGame.winner();
    }
}
