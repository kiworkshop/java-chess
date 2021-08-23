package chess.domain.movement;

import chess.domain.board.Team;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public abstract class Movement {
    protected Position position;

    public boolean canMove(Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);

        List<Position> movablePositions = source.getMovablePositions();
        if (movablePositions.isEmpty()) {
            if (source instanceof Pawn) {
                boolean firstMovePosition = isPawnFirstMovement(source.position, target.position);
                boolean attackPositions = isPawnAttackMovement(source.position, target.position);
                return firstMovePosition || attackPositions;
            }
            return false;
        }
        return movablePositions.contains(target.position);
    }

    public abstract List<Position> getMovablePositions();

    public void move(Position target) {
        this.position = target;
    }

    private boolean isPawnAttackMovement(Position source, Position target) {
        return (Math.abs(source.fileNumber() - target.fileNumber()) == 1) && (source.fileNumber() == target.fileNumber());
    }

    private boolean isPawnFirstMovement(Position source, Position target) {
        return (source.fileNumber() == target.fileNumber()) && (Math.abs(source.rankNumber() - target.rankNumber()) == 2);
    }

    protected boolean isNotSelf(Position source, Position target) {
        return source != target;
    }

    private void withoutSameTeam(Team sourceTeam, Piece targetPiece) {
        Team targetTeam = targetPiece.team();
        if (sourceTeam.equals(targetTeam)) {
            throw new IllegalArgumentException("아군이 있는 칸에는 이동할 수 없습니다.");
        }
    }

    private void notBlankPosition(Piece sourcePiece) {
        if (sourcePiece.team().equals(Team.NEUTRAL)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
    }

    public Position position() {
        return position;
    }
}
