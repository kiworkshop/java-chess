package chess.domain.game;

import chess.domain.team.Color;

public class Player {

    private static final String DEFAULT_WHITE_NAME = "WHITE";
    private static final String DEFAULT_BLACK_NAME = "BLACK";

    private final String name;

    public static Player of(final String name) {
        return new Player(name);
    }

    public static Player defaultPlayer(final Color color) {
        if (color.isWhite()) {
            return defaultWhite();
        }
        return defaultBlack();
    }

    private static Player defaultWhite() {
        return Player.of(DEFAULT_WHITE_NAME);
    }

    private static Player defaultBlack() {
        return Player.of(DEFAULT_BLACK_NAME);
    }

    private Player(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
