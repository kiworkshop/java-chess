package chess.domain;

import chess.domain.player.EnumTeam;
import chess.model.Team;
import chess.model.Turn;

import java.util.HashMap;
import java.util.Map;

public class TurnImpl implements Turn {

    private static final Map<Team, Team> REVERSE_TURN;

    static {
        REVERSE_TURN = new HashMap<>();
        REVERSE_TURN.put(EnumTeam.WHITE, EnumTeam.BLACK);
        REVERSE_TURN.put(EnumTeam.BLACK, EnumTeam.WHITE);
    }

    private Team team;

    public TurnImpl(Team team) {
        this.team = team;
    }

    @Override
    public boolean sameTurn(Team team) {
        return this.team.equals(team);
    }

    @Override
    public void changeTurn() {
        team = REVERSE_TURN.get(team);
    }
}
