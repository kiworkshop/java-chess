package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece implements Movable{

    protected final boolean white;
    protected char name;

    protected Piece(boolean white) {
        this.white = white;
    }
}
