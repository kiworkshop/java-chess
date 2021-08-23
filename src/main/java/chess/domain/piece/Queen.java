package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

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
        return Position.all().stream()
                .filter(target -> (isQueenMovement(position, target)) && isNotSelf(position, target))
                .collect(Collectors.toList());
    }

    private boolean isQueenMovement(Position source, Position target) {
        return ((source.fileNumber() - target.fileNumber()) == 0 || (source.rankNumber() - target.rankNumber()) == 0)
                || ((Math.abs(source.fileNumber() - target.fileNumber())) - (Math.abs(source.rankNumber() - target.rankNumber())) == 0);
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
