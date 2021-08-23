package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

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
        return Position.all().stream()
                .filter(target -> isPawnMovement(position, target))
                .collect(Collectors.toList());
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
