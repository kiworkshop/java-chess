package chess.domain.piece;

public class King extends Piece {

    public King(Team team, PiecePosition piecePosition) {
        super("K", team, piecePosition);
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
