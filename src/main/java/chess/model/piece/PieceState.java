package chess.model.piece;

import chess.model.board.BoardState;
import chess.model.postiion.Position;

import java.util.Set;

public interface PieceState {

    PieceState move(Position target, BoardState boardState);

    Set<Position> movablePosition(BoardState boardState);

}
