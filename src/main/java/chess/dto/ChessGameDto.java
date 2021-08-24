package chess.dto;

import chess.domain.board.Board;
import chess.domain.state.GameState;
import chess.game.ChessGame;

public class ChessGameDto {
    private final GameState gameState;

    public ChessGameDto(ChessGame chessGame) {
        this.gameState = chessGame.gameState();
    }

    public Board board() {
        return gameState.board();
    }
}
