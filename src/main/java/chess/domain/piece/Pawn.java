package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final String PAWN_SYMBOL = "p";
    private static final int PAWN_SCORE = 1;
    public static final double PAWN_SAME_FILE_SCORE = 0.5;

    private Pawn(Team team, Position position) {
        super(team, position);
    }

    public static Pawn of(Team team, Position position) {
        return new Pawn(team, position);
    }

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);

        List<Position> movablePositions = source.getMovablePositions();
        if (movablePositions.contains(target.position())) {
            return true;
        }

        if (isPawnAttack(source.position(), target.position())) {
            return true;
        }
        return  isPawnFirstMove(source.position(), target.position());
    }

    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> moveStrategy(position, target))
                .collect(Collectors.toList());
    }

    @Override
    public boolean moveStrategy(Position source, Position target) {
        if (team.equals(Team.WHITE)) {
            return (source.fileNumber() == target.fileNumber()) && (target.rankNumber() - source.rankNumber() == 1);
        }
        return (source.fileNumber() == target.fileNumber()) && (source.rankNumber() - target.rankNumber() == 1);
    }

    private boolean isPawnAttack(Position source, Position target) {
        if (team.equals(Team.WHITE)) {
            return (Math.abs(source.fileNumber() - target.fileNumber()) == 1) && (target.rankNumber() - source.rankNumber() == 1);
        }
        return (Math.abs(source.fileNumber() - target.fileNumber()) == 1) && (source.rankNumber() - target.rankNumber() == 1);
    }

    private boolean isPawnFirstMove(Position source, Position target) {
        if (team.equals(Team.WHITE)) {
            return (source.rankNumber() == 2) && (target.rankNumber() - source.rankNumber() == 2);
        }
        return (source.rankNumber() == 7) && (source.rankNumber() - target.rankNumber() == 2);
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return PAWN_SYMBOL.toUpperCase();
        }
        return PAWN_SYMBOL;
    }
}
