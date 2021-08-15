package chess.domain.piece;

import lombok.Getter;

@Getter
public class King extends Piece {

    public King(Team team) {
        super("K", team);
        if(team.equals(Team.BLACK)) {
            super.piecePosition = new PiecePosition(File.E, Rank.ONE);
        }
        if(team.equals(Team.WHITE)) {
            this.piecePosition = new PiecePosition(File.E, Rank.EIGHT);
        }
    }
}
