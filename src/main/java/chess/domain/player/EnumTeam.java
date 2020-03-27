package chess.domain.player;

import chess.model.Team;

public enum EnumTeam implements Team {

    WHITE,
    BLACK;

    @Override
    public boolean isAlly(Team team) {
        return equals(team);
    }

    @Override
    public boolean isEnemy(Team team) {
        return !equals(team);
    }

    @Override
    public boolean isWhite() {
        return this == WHITE;
    }

    @Override
    public boolean isBlack() {
        return this == BLACK;
    }
}
