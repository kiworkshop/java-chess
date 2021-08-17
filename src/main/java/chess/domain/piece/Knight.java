package chess.domain.piece;

public class Knight extends Piece {

    public Knight(Team team, PiecePosition piecePosition) {
        super("N", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        return false;
    }
}
