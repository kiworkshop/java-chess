package chess.game;

import chess.domain.board.Team;

public class Turn {
    private final Team turn;

    private Turn(Team team) {
        this.turn = team;
    }

    public static Turn of(Team team) {
        return new Turn(team);
    }

    public Turn toggle() {
        if (turn.equals(Team.BLACK)) {
            return Turn.of(Team.WHITE);
        }
        return Turn.of(Team.BLACK);
    }

    public Team team() {
        return turn;
    }
}
