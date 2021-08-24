package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final String ROOK_SYMBOL = "r";
    private static final int ROOK_SCORE = 5;

    private Rook(Team team, Position position) {
        super(team, position);
    }

    public static Rook of(Team team, Position position) {
        return new Rook(team, position);
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
        return (source.fileNumber() - target.fileNumber()) == 0 || (source.rankNumber() - target.rankNumber()) == 0;
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return ROOK_SYMBOL.toUpperCase();
        }
        return ROOK_SYMBOL;
    }
}
