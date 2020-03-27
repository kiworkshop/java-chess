package chess.domain.piece;

import chess.domain.player.EnumTeam;

public class PieceDto {

    private EnumPieceType pieceType;
    private EnumTeam team;

    public PieceDto(EnumPieceType pieceType, EnumTeam team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public EnumTeam getTeam() {
        return team;
    }
}
