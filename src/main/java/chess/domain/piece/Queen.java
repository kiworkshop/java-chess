package chess.domain.piece;

import chess.domain.team.Team;
import lombok.Getter;

@Getter
public class Queen extends Piece {
    public Queen(Team team, PiecePosition pieceposition) {
        super("Q", team, pieceposition, 9);
    }

    @Override
    public boolean canMoveTo(PiecePosition targetPosition) {
        int originFile = this.piecePosition.getFile().getFilePosition();
        int originRank = this.piecePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        return (fileGap == rankGap) || (fileGap == 0 || rankGap == 0);
    }


}
