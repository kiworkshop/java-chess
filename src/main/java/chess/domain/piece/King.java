package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

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
        return Position.all().stream()
                .filter(target -> isKingMovement(position, target))
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
