package chess.dto;

import chess.domain.board.Board;
import chess.domain.state.GameState;
import chess.game.ChessGame;

public class ChessGameDto {
    private final GameState gameState;
    private final Board board;

    public ChessGameDto(ChessGame chessGame) {
        this.gameState = chessGame.state();
        this.board = chessGame.board();
    }

    public Board board() {
        return board;
    }
}
