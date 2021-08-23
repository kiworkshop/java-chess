package chess.domain.board;

public enum Team {
    WHITE("백"),
    BLACK("흑"),
    NEUTRAL("");

    private final String color;

    Team(String color) {
        this.color = color;
    }

    public String color() {
        return color;
    }
}
