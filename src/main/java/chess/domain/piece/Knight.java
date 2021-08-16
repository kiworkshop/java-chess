package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Knight extends Piece {

    private Knight(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Knight(position, player);
    }
}
