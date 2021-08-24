package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends Piece {
    private static final String KING_SYMBOL = "k";
    private static final int KING_SCORE = 0;

    private King(Team team, Position position) {
        super(team, position);
    }

    public static King of(Team team, Position position) {
        return new King(team, position);
    }

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        List<Position> movablePositions = source.getMovablePositions();

        Set<Position> checkmatePositions = checkmatePositions(board);
        if (checkmatePositions.contains(target.position())) {
            return false;
        }
        return movablePositions.contains(target.position());
    }

    private Set<Position> checkmatePositions(Board board) {
        Set<Position> checkmatePositions = new HashSet<>();
        Team otherTeam = team.equals(Team.WHITE) ? Team.BLACK : Team.WHITE;
        List<Piece> otherPieces = board.findBy(otherTeam);
        for (Piece otherPiece : otherPieces) {
            checkmatePositions.addAll(otherPiece.getMovablePositions());
        }
        return checkmatePositions;
    }

    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> moveStrategy(position, target))
                .collect(Collectors.toList());
    }

    @Override
    public boolean moveStrategy(Position source, Position target) {
        return ((Math.abs(source.fileNumber() - target.fileNumber())) == 1 && (Math.abs(source.rankNumber() - target.rankNumber()) == 1))
                || ((source.fileNumber() == target.fileNumber()) && (Math.abs(source.rankNumber() - target.rankNumber()) == 1))
                || ((Math.abs(source.fileNumber() - target.fileNumber())) == 1 && (source.rankNumber() == target.rankNumber()));
    }

    @Override
    public double score() {
        return KING_SCORE;
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return KING_SYMBOL.toUpperCase();
        }
        return KING_SYMBOL;
    }
}
