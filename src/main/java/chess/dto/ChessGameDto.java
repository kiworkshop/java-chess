package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;

import java.util.Collections;
import java.util.Map;

public class ChessGameDto {
    private GameState gameState;
    private Map<Position, Piece> board;

    public ChessGameDto(GameState gameState, Map<Position, Piece> board) {
        this.gameState = gameState;
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece from(Position position) {
        return board.get(position);
    }

}
