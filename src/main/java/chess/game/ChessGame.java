package chess.game;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

public class ChessGame {
    private GameState gameState;

    private ChessGame(GameState gameState) {
        this.gameState = gameState;
    }

    public static ChessGame of(GameState gameState) {
        return new ChessGame(gameState);
    }

    public static ChessGame ready() {
        return ChessGame.of(new Ready());
    }

    public ChessGame start() {
        this.gameState = gameState.start();
        return this;
    }

    public ChessGame move(Position source, Position target) {
        Board board = gameState.board();
        this.gameState = gameState.moveAndToggleTurn(board.from(source), board.from(target));
        return this;
    }

    public ChessGame end() {
        this.gameState = gameState.end();
        return this;
    }

    public GameState state() {
        return gameState;
    }

    public Board board() {
        return gameState.board();
    }
}
