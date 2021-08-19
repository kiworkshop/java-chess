package chess.game;

import chess.domain.board.Team;

public class Turn {
    private Team turn;

    private Turn(Team team) {
        this.turn = team;
    }

    public static Turn of(Team team) {
        return new Turn(team);
    }

    public Team toggle() {
        if (turn.equals(Team.BLACK)) {
            this.turn = Team.WHITE;
            return turn;
        }
        this.turn = Team.BLACK;
        return turn;
    }
}
