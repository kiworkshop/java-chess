package mychess.domain.player;

public enum Player {

    BLACK("Black"),
    WHITE("White"),
    NONE("None");

    private String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
