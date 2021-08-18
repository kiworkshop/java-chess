package chess.domain.piece;

public class Bishop extends Piece {

    public Bishop(Team team, PiecePosition piecePosition) {
        super("B", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = super.piecePosition.getFile().getFilePosition();
        int originRank = super.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return (fileGap == rankGap);
    }
}
