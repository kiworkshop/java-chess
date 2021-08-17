package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Bishop extends Piece {
    private Bishop(Team team, Position position) {
        super(team, position);
    }

    public static Bishop of(Team team, Position position) {
        return new Bishop(team, position);
    }
}
