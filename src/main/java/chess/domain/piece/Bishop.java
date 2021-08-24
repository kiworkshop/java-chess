package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    private static final String BISHOP_SYMBOL = "b";
    private static final int BISHOP_SCORE = 3;

    private Bishop(Team team, Position position) {
        super(team, position);
    }

    public static Bishop of(Team team, Position position) {
        return new Bishop(team, position);
    }

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        disableJump(board.values());

        List<Position> movablePositions = source.getMovablePositions();
        return movablePositions.contains(target.position());
    }


    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> moveStrategy(position, target) && isNotSelf(position, target))
                .collect(Collectors.toList());
    }

    @Override
    public boolean moveStrategy(Position source, Position target) {
        return (Math.abs(source.fileNumber() - target.fileNumber())) - (Math.abs(source.rankNumber() - target.rankNumber())) == 0;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return BISHOP_SYMBOL.toUpperCase();
        }
        return BISHOP_SYMBOL;
    }
}

