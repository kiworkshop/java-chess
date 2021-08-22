package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;

public class Knight extends Piece {
    private final String symbol = "n";

    private Knight(Team team, Position position) {
        super(team, position);
    }

    public static Knight of(Team team, Position position) {
        return new Knight(team, position);
    }

    @Override
    public List<Position> getMovablePositions() {
        return null;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}