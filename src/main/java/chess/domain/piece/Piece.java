package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.Movement;
import chess.domain.position.Position;

public abstract class Piece extends Movement {
    protected Team team;
    protected Position position;

    protected Piece(Team team, Position position) {
        this.team = team;
        this.position = position;
    }

    public void move(Position target) {
        this.position = target;
    }

    public Position position() {
        return position;
    }
}
