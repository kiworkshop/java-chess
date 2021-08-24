package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private static final String KNIGHT_SYMBOL = "n";
    private static final double KNIGHT_SCORE = 2.5;

    private Knight(Team team, Position position) {
        super(team, position);
    }

    public static Knight of(Team team, Position position) {
        return new Knight(team, position);
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
                .filter(target -> isKnightMovement(position, target) && isNotSelf(position, target))
                .collect(Collectors.toList());
    }

    private boolean isKnightMovement(Position source, Position target) {
        return Math.abs(source.fileNumber() - target.fileNumber()) == 2 && Math.abs(source.rankNumber() - target.rankNumber()) == 1
                || Math.abs(source.fileNumber() - target.fileNumber()) == 1 && Math.abs(source.rankNumber() - target.rankNumber()) == 2;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return KNIGHT_SYMBOL.toUpperCase();
        }
        return KNIGHT_SYMBOL;
    }
}