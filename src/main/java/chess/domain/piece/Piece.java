package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.movement.MoveStrategy;
import chess.domain.position.Position;

import java.util.Set;

public abstract class Piece {
    private final MoveStrategy moveStrategy;
    private final Team team;
    private Position position;

    protected Piece(Team team, Position position, MoveStrategy movement) {
        this.team = team;
        this.position = position;
        this.moveStrategy = movement;
    }

    public boolean canMove(Board board, Piece target) {
        return moveStrategy.canMove(board, this, target);
    }

    public Set<Position> getMovablePositions(Board board) {
        return moveStrategy.getMovablePositions(board, this);
    }

    public void move(Position target) {
        this.position = target;
    }

    public abstract String symbol();

    public String getSymbol() {
        if (team.equals(Team.BLACK)) {
            return symbol().toUpperCase();
        }
        return symbol();
    }

    public abstract double score();

    public Team getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public int getFileNumber() {
        return position.getFileNumber();
    }
}
