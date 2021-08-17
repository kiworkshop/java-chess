package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Knight extends Piece {
    private Knight(Team team, Position position) {
        super(team, position);
    }

    public static Knight of(Team team, Position position) {
        return new Knight(team, position);
    }
}
