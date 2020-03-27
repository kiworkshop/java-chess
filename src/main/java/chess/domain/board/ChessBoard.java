package chess.domain.board;

import chess.model.board.Board;
import chess.model.board.BoardInitializer;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Map;

public class ChessBoard implements Board {

    private Map<Position, PieceState> board;

    private ChessBoard(Map<Position, PieceState> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new ChessBoard(boardInitializer.create());
    }

    @Override
    public void move(Position source, Position target) {

    }
}
