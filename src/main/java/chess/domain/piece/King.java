package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;

public class King extends Piece {
    private final String symbol = "k";

    private King(Team team, Position position) {
        super(team, position);
    }

    public static King of(Team team, Position position) {
        return new King(team, position);
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
