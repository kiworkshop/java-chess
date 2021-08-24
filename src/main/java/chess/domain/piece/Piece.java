package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.Movement;
import chess.domain.position.Position;

public abstract class Piece extends Movement {
    protected Team team;

    protected Piece(Team team, Position position) {
        this.team = team;
        this.position = position;
    }

    public abstract String symbol();

    public abstract double score();

    public Team team() {
        return team;
    }
}
