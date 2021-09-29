package chess.domain.game;

import chess.domain.team.Color;

import java.util.*;

public class Turn {

    private final Map<Color, List<Player>> players;
    private Color color;
    private int count;

    public static Turn initialTurn(final Map<Color, List<Player>> players) {
        return new Turn(players);
    }

    public static Turn initialTurn() {
        return initialTurn(defaultPlayers());
    }

    private Turn(final Map<Color, List<Player>> players) {
        this.players = Collections.unmodifiableMap(players);
        this.color = Color.WHITE;
        this.count = 0;
    }

    private static Map<Color, List<Player>> defaultPlayers() {
        Map<Color, List<Player>> map = new EnumMap<>(Color.class);
        Arrays.stream(Color.values())
                .forEach(color -> map.put(color, Collections.singletonList(Player.defaultPlayer(color))));
        return map;
    }

    public void next() {
        color = color.flip();
        count++;
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public Color team() {
        return color;
    }

    public Player player() {
        List<Player> candidates = this.players.get(color);
        int index = count / 2 % candidates.size();
        return candidates.get(index);
    }
}
