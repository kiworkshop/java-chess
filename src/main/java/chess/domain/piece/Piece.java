package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public abstract class Piece {

    protected Position position;
    protected Player player;

    protected Piece(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    public Position getPosition() {
        return position;
    }
}
