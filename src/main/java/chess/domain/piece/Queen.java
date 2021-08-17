package chess.domain.piece;

public class Queen extends Piece {

    public Queen(Team team, PiecePosition pieceposition) {
        super("Q", team, pieceposition);
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
