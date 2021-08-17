package chess.domain.piece;

public class Rook extends Piece {

    public Rook(Team team, PiecePosition piecePosition) {
        super("R", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        return false;
    }
}
