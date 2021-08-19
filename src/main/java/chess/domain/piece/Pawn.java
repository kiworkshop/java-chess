package chess.domain.piece;

import lombok.Getter;

@Getter
public class Pawn extends Piece {
    public Pawn(Team team, PiecePosition piecePosition) {
        super("P", team, piecePosition,1);
    }

    @Override
    public boolean movable(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        //처음 두칸
        if (checkFirstMove()) {
            if (this.getTeam().equals(Team.BLACK)) {
                return ((originFile == targetFile) && (originRank - targetRank <= 2))
                        || canMoveDiagonalAndOneBlock(originFile, originRank, targetFile, targetRank);
            }
            if (this.getTeam().equals(Team.WHITE)) {
                return (originFile == targetFile) && (originRank - targetRank >= -2)
                        || canMoveDiagonalAndOneBlock(originFile, originRank, targetFile, targetRank);
            }
        }
        //한칸
        return canMoveDiagonalAndOneBlock(originFile, originRank, targetFile, targetRank);
    }

    private boolean canMoveDiagonalAndOneBlock(int originFile, int originRank, int targetFile, int targetRank) {
        return (originRank - targetRank == 1*this.getTeam().getPawnDirection()) && Math.abs(originFile - targetFile) <= 1;
    }

    private boolean checkFirstMove() {
        return this.getPiecePosition().getRank().equals(this.getTeam().getPawnInitRank());
    }
}
