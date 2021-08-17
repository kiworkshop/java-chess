package chess.domain.piece;

public class King extends Piece {
    public King(Team team, PiecePosition piecePosition) {
        super("K", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = super.piecePosition.getFile().getFilePosition();
        int originRank = super.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        //상하좌우 한칸씩
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return fileGap + rankGap == 1;
    }
}
