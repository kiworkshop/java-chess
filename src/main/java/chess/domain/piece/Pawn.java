package chess.domain.piece;

public class Pawn extends Piece {
    public Pawn(Team team, PiecePosition piecePosition) {
        super("P", team, piecePosition);
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
