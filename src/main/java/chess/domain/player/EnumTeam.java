package chess.domain.player;

import chess.model.Team;

public enum EnumTeam implements Team {

    WHITE,
    BLACK;

    @Override
    public boolean isSameTeam(Team team) {
        return this == team;
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
