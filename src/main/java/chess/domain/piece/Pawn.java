package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private Pawn(Team team, Position position) {
        super(team, position);
    }

    public static Pawn of(Team team, Position position) {
        return new Pawn(team, position);
    }
}
