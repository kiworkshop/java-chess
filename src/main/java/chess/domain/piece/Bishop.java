package chess.domain.piece;

import chess.domain.team.Team;
import lombok.Getter;

@Getter
public class Bishop extends Piece {
    public Bishop(Team team, PiecePosition piecePosition) {
        super("B", team, piecePosition,3);
    }

    @Override
    public boolean canMoveTo(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return (fileGap == rankGap);
    }
}
