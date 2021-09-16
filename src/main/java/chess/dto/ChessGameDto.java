package chess.dto;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.state.GameState;

public class ChessGameDto {
    private final GameState gameState;

    public ChessGameDto(ChessGame chessGame) {
        this.gameState = chessGame.gameState();
    }

    public Board board() {
        return gameState.getBoard();
    }
}
