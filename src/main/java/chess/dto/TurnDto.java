package chess.dto;

import chess.domain.game.Player;
import chess.domain.game.Turn;
import chess.domain.team.Color;

public class TurnDto {

    private final String team;
    private final String playerName;

    private TurnDto(String team, String playerName) {
        this.team = team;
        this.playerName = playerName;
    }

    public static TurnDto of(Turn currentTurn) {
        Color color = currentTurn.team();
        Player player = currentTurn.player();
        return new TurnDto(color.name(), player.name());
    }

    public String getTeam() {
        return team;
    }

    public String getPlayerName() {
        return playerName;
    }
}
