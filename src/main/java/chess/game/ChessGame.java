package chess.game;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.Finish;
import chess.domain.state.GameState;
import chess.dto.ChessGameDto;

public class ChessGame {
    private GameState gameState;
    private Board board;

    public ChessGameDto startGame(GameState gameState) {
        this.gameState = gameState;
        this.board = gameState.getBoard();
        return new ChessGameDto(gameState, board.values());
    }

    public ChessGameDto move(Position source, Position target) {
        this.gameState = gameState.start();
        board.move(source, target);
        return new ChessGameDto(gameState, board.values());
    }

    public ChessGameDto endGame() {
        Finish finish = new Finish();
        changeChessGame(finish);
        return new ChessGameDto(gameState, board.values());
    }

    private void changeChessGame(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }
}
