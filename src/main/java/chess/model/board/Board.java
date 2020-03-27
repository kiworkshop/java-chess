package chess.model.board;

import chess.model.postiion.Position;

public interface Board {

    void move(Position source, Position target);

}
