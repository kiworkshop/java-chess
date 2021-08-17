package chess.domain.piece;

public class Bishop extends Piece {

    public Bishop(Team team, PiecePosition piecePosition) {
        super("B", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        return false;
    }

    @Override
    public Piece move(PiecePosition tarPiecePosition) {
        return null;
    }
}
