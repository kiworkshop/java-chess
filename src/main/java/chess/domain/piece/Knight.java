package chess.domain.piece;

import lombok.Getter;

@Getter
public class Knight extends Piece {
    private final double score = 2.5;

    public Knight(Team team, PiecePosition piecePosition) {
        super("N", team, piecePosition);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        //상하좌우 한칸씩
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return (fileGap + rankGap == 3) && (fileGap == 1 || rankGap == 1);
    }
}
