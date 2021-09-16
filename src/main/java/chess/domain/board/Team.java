package chess.domain.board;

public enum Team {
    WHITE("백"),
    BLACK("흑"),
    NEUTRAL("");

    private final String color;

    Team(String color) {
        this.color = color;
    }

    public Team otherTeam() {
        if (this.equals(Team.WHITE)) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public boolean isSameTeam(Team team) {
        return color.equals(team.getColor());
    }

    public String getColor() {
        return color;
    }
}
