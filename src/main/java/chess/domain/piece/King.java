package chess.domain.piece;

import lombok.Getter;

@Getter
public class King {
    private String name = "K";
    private Team team;
    private PiecePosition piecePosition;
    private String displayName;
    public King(Team team) {
        this.team = team;
        this.displayName = team.displayName(name);
        if(team.equals(Team.BLACK)) {
            this.piecePosition = new PiecePosition(File.E, Rank.ONE);
        }
        if(team.equals(Team.WHITE)) {
            this.piecePosition = new PiecePosition(File.E, Rank.EIGHT);
        }
    }
}
