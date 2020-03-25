package mychess.domain.player;

import mychess.domain.Color;

public class Player {

    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public String getColor() {
        return color.getColorName();
    }
}
