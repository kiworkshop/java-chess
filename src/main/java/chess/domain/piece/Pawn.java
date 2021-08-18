package chess.domain.piece;

public class Pawn extends Piece {
    public Pawn(Team team, PiecePosition piecePosition) {
        super("P", team, piecePosition);
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
                        || (originRank - targetRank == 1) && Math.abs(originFile - targetFile) <= 1;
            }
            if (this.getTeam().equals(Team.WHITE)) {
                return (originFile == targetFile) && (originRank - targetRank >= -2)
                        || (originRank - targetRank == -1) && Math.abs(originFile - targetFile) <= 1;
            }
        }
        //한칸
        if (this.getTeam().equals(Team.BLACK)) {
            return (originRank - targetRank == 1 && Math.abs(originFile - targetFile) <= 1);
        }
        if (this.getTeam().equals(Team.WHITE)) {
            return (originRank - targetRank == -1 && Math.abs(originFile - targetFile) <= 1);
        }

        //대각선
        return false;
    }

    private boolean checkFirstMove() {
        if (this.getTeam().equals(Team.BLACK)) {
            return this.getPiecePosition().getRank().equals(Rank.SEVEN);
        }

        if (this.getTeam().equals(Team.WHITE)) {
            return this.getPiecePosition().getRank().equals(Rank.TWO);
        }
        return false;
    }
}
