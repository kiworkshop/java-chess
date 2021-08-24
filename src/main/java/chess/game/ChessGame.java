package chess.game;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

import java.util.List;

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

    public ChessGame moveAndToggleTurn(Position source, Position target) {
        Board board = gameState.board();
        this.gameState = gameState.move(board.from(source), board.from(target));

        Turn turn = gameState.toggle();
        if (!board.existKing(turn)) {
            this.gameState = gameState.end();
        }
        return this;
    }

    public List<String> end() {
        this.gameState = gameState.end();
        return winner();
    }

    public List<String> winner() {
        Score score = status();
        return gameState.winner(score);
    }

    public Score status() {
        double whiteScore = gameState.status(Team.WHITE);
        double blackScore = gameState.status(Team.BLACK);
        return Score.of(whiteScore, blackScore);
    }

    public GameState gameState() {
        return gameState;
    }

    public Board board() {
        return gameState.board();
    }
}
