package chess.domain.movement;

import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class Movement {
    protected Position position;

    public boolean canMove(Piece source, Piece target) {
        validateSource(source);
        validateTarget(source.team(), target);
        return true;
    }

    public void move(Position target) {
        this.position = target;
    }

    public Position position() {
        return position;
    }

    private void validateTarget(Team sourceTeam, Piece targetPiece) {
        Team targetTeam = targetPiece.team();
        if (sourceTeam.equals(targetTeam)) {
            throw new IllegalArgumentException("아군이 있는 칸에는 이동할 수 없습니다.");
        }
    }

    private void validateSource(Piece sourcePiece) {
        if (sourcePiece.team().equals(Team.NEUTRAL)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
    }
}
