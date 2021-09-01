package chess.domain.piece;

import lombok.Getter;

@Getter
public class Rook extends Piece {
    public Rook(Team team, PiecePosition piecePosition) {
        super("R", team, piecePosition, 5);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return fileGap == 0 || rankGap == 0;
    }
}
