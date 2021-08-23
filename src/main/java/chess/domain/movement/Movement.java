package chess.domain.movement;

import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public abstract class Movement {
    protected Position position;

    public boolean canMove(Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        return true;
    }

    public abstract List<Position> getMovablePositions();

    public void move(Position target) {
        this.position = target;
    }

    protected boolean isRookMovement(Position source, Position target) {
        return (source.fileNumber() - target.fileNumber()) == 0 || (source.rankNumber() - target.rankNumber()) == 0;
    }

    protected boolean isBishopMovement(Position source, Position target) {
        return (Math.abs(source.fileNumber() - target.fileNumber())) - (Math.abs(source.rankNumber() - target.rankNumber())) == 0;
    }

    protected boolean isKnightMovement(Position source, Position target) {
        return Math.abs(source.fileNumber() - target.fileNumber()) == 2 && Math.abs(source.rankNumber() - target.rankNumber()) == 1
                || Math.abs(source.fileNumber() - target.fileNumber()) == 1 && Math.abs(source.rankNumber() - target.rankNumber()) == 2;
    }

    protected boolean isNotSelf(Position source, Position target) {
        return source != target;
    }

    protected void withoutSameTeam(Team sourceTeam, Piece targetPiece) {
        Team targetTeam = targetPiece.team();
        if (sourceTeam.equals(targetTeam)) {
            throw new IllegalArgumentException("아군이 있는 칸에는 이동할 수 없습니다.");
        }
    }

    protected void notBlankPosition(Piece sourcePiece) {
        if (sourcePiece.team().equals(Team.NEUTRAL)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
    }

    public Position position() {
        return position;
    }
}
