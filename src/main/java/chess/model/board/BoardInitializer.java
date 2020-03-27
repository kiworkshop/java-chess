package chess.model.board;

import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Map;

public interface BoardInitializer {

    Map<Position, PieceState> create();

}
