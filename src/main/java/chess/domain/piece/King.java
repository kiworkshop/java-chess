package chess.domain.piece;

import lombok.Getter;

@Getter
public class King {
    private Team team;
    private String position;
    private String displayName;
    public King(Team team) {
        this.team = team;
        if(team.equals(Team.BLACK)) {
            this.position = "E1";
            this.displayName = "k";
        }

        if(team.equals(Team.WHITE)) {
            this.position = "E8";
            this.displayName = "K";
        }
    }
}
