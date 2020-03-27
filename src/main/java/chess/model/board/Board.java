package chess.model.board;

import chess.model.Turn;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

public interface Board {

    void move(Position source, Position target, Turn turn);

    PieceState getPieceState(Position position);

}
