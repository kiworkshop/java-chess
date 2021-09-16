package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KingMoveStrategy extends MoveStrategy {
    private static final List<Direction> KING_DIRECTIONS = Direction.KING_DIRECTIONS;

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        checkBlankPosition(source);
        checkSameTeamPosition(source.getTeam(), target.getTeam());

        Set<Position> movablePositions = getMovablePositions(board, source);
        checkmatePosition(board, source, target);
        return checkMovablePosition(movablePositions, target.getPosition());
    }

    @Override
    public Set<Position> getMovablePositions(Board board, Piece source) {
        return onceMovablePositions(KING_DIRECTIONS, source.getPosition()).stream()
                .filter(target -> board.isNotSameTeam(source.getPosition(), target))
                .collect(Collectors.toSet());
    }

    private void checkmatePosition(Board board, Piece source, Piece target) {
        Set<Position> checkmatePositions = checkmatePositions(board, source.getTeam());
        if (checkmatePositions.contains(target.getPosition())) {
            throw new IllegalArgumentException("체크메이트 위치로 이동할 수 없습니다.");
        }
    }

    private Set<Position> checkmatePositions(Board board, Team team) {
        Set<Position> checkmatePositions = new HashSet<>();
        List<Piece> otherPieces = board.findBy(team.otherTeam());
        for (Piece otherPiece : otherPieces) {
            checkmatePositions.addAll(otherPiece.getMovablePositions(board));
        }
        return checkmatePositions;
    }
}
