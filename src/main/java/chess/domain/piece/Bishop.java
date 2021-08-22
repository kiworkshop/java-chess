package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    private final String symbol = "b";

    private Bishop(Team team, Position position) {
        super(team, position);
    }

    public static Bishop of(Team team, Position position) {
        return new Bishop(team, position);
    }

    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> diagonalAxis(position, target) && isNotSelf(position, target))
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

