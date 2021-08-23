package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public boolean canMove(Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        List<Position> movablePositions = source.getMovablePositions();

        Set<Position> checkmatePositions = new HashSet<>();
        List<Piece> otherPieces = Board.otherTeamPiece(source.team());
        for (Piece otherPiece : otherPieces) {
            checkmatePositions.addAll(otherPiece.getMovablePositions());
        }

        if (checkmatePositions.contains(target.position())) {
            return false;
        }
        return movablePositions.contains(target.position());
    }

    @Override
    public List<Position> getMovablePositions() {
        return Position.all().stream()
                .filter(target -> isKingMovement(position, target))
                .collect(Collectors.toList());
    }

    private boolean isKingMovement(Position source, Position target) {
        return ((Math.abs(source.fileNumber() - target.fileNumber())) == 1 && (Math.abs(source.rankNumber() - target.rankNumber()) == 1))
                || ((source.fileNumber() == target.fileNumber()) && (Math.abs(source.rankNumber() - target.rankNumber()) == 1))
                || ((Math.abs(source.fileNumber() - target.fileNumber())) == 1 && (source.rankNumber() == target.rankNumber()));
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
