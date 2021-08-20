package chess.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

public class ChessGame {
    private GameState gameState;
    private Board board;

    private ChessGame(GameState gameState, Board board) {
        this.gameState = gameState;
        this.board = board;
    }

    public static ChessGame of(GameState gameState, Board board) {
        return new ChessGame(gameState, board);
    }

    public ChessGame ready() {
        Ready ready = new Ready();
        return new ChessGame(ready, ready.board());
    }

    public ChessGame start() {
        GameState start = gameState.start();
        return new ChessGame(start, start.board());
    }

    public ChessGame move(Piece source, Piece target) {
        GameState playing = gameState.moveAndToggleTurn(source, target);
        return new ChessGame(playing, playing.board());
    }

    public ChessGame end() {
        GameState end = gameState.end();
        return new ChessGame(end, end.board());
    }

    public GameState state() {
        return gameState;
    }

    public Board board() {
        return board;
    }
}
