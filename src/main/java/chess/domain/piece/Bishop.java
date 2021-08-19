package chess.domain.piece;

import lombok.Getter;

@Getter
public class Bishop extends Piece {
    private final double score = 3;

    public Bishop(Team team, PiecePosition piecePosition) {
        super("B", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return (fileGap == rankGap);
    }
}
