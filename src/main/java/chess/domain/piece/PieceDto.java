package chess.domain.piece;

import chess.domain.player.EnumTeam;
import chess.model.Team;
import chess.model.piece.PieceState;
import chess.model.piece.PieceType;

public class PieceDto {

    private PieceType pieceType;
    private Team team;

    public PieceDto(EnumPieceType pieceType, EnumTeam team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    private PieceDto(PieceType pieceType, Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public static PieceDto from(PieceState pieceState) {
        return new PieceDto(pieceState.getPieceType(), pieceState.getTeam());
    }

    public Team getTeam() {
        return team;
    }
}
