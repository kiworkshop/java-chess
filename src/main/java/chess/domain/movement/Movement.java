package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Movement {
    protected Position position;

    public abstract boolean canMove(Board board, Piece source, Piece target);

    public abstract boolean moveStrategy(Position source, Position target);

    public abstract List<Position> getMovablePositions();

    public void move(Position target) {
        this.position = target;
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
        if (isBlank(sourcePiece)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
    }

    protected void disableJump(Map<Position, Piece> board) {
        List<Piece> pieces = board.values().stream()
                .filter(piece -> moveStrategy(position, piece.position) && isNotSelf(position, piece.position))
                .collect(Collectors.toList());

        pieces.forEach(piece -> disableJump(piece));
    }

    private void disableJump(Piece piece) {
        if (!isBlank(piece)) {
            throw new IllegalArgumentException("다른 체스 말을 뛰어넘을 수 없습니다.");
        }
    }

    private boolean isBlank(Piece piece) {
        return piece.team().equals(Team.NEUTRAL);
    }

    public Position position() {
        return position;
    }
}
