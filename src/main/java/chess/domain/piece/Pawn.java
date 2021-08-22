package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private final String symbol = "p";

    private Pawn(Team team, Position position) {
        super(team, position);
    }

    public static Pawn of(Team team, Position position) {
        return new Pawn(team, position);
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
