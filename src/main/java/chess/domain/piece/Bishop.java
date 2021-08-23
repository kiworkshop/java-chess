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
    public boolean canMove(Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        List<Position> movablePositions = source.getMovablePositions();
        return movablePositions.contains(target.position());
    }

    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> isBishopMovement(position, target) && isNotSelf(position, target))
                .collect(Collectors.toList());
    }

    private boolean isBishopMovement(Position source, Position target) {
        return (Math.abs(source.fileNumber() - target.fileNumber())) - (Math.abs(source.rankNumber() - target.rankNumber())) == 0;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}

