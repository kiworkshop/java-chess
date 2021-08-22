package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece {
    private final String symbol = "q";

    private Queen(Team team, Position position) {
        super(team, position);
    }

    public static Queen of(Team team, Position position) {
        return new Queen(team, position);
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
