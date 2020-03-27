package chess.domain.board;

import chess.domain.piece.implementation.Bishop;
import chess.domain.piece.implementation.King;
import chess.domain.piece.implementation.Knight;
import chess.domain.piece.implementation.NotMovedPawn;
import chess.domain.piece.implementation.Queen;
import chess.domain.piece.implementation.Rook;
import chess.domain.player.EnumTeam;
import chess.domain.position.EnumPosition;
import chess.model.Team;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.function.BiFunction;

public enum InitialPieceRepository {

    BLACK_PAWN_1(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("A7")),
    BLACK_PAWN_2(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("B7")),
    BLACK_PAWN_3(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("C7")),
    BLACK_PAWN_4(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("D7")),
    BLACK_PAWN_5(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("E7")),
    BLACK_PAWN_6(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("F7")),
    BLACK_PAWN_7(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("G7")),
    BLACK_PAWN_8(NotMovedPawn::of, EnumTeam.BLACK, EnumPosition.from("H7")),
    BLACK_ROOK_1(Rook::of, EnumTeam.BLACK, EnumPosition.from("A8")),
    BLACK_KNIGHT_1(Knight::of, EnumTeam.BLACK, EnumPosition.from("B8")),
    BLACK_BISHOP_1(Bishop::of, EnumTeam.BLACK, EnumPosition.from("C8")),
    BLACK_QUEEN(Queen::of, EnumTeam.BLACK, EnumPosition.from("D8")),
    BLACK_KING(King::of, EnumTeam.BLACK, EnumPosition.from("E8")),
    BLACK_BISHOP_2(Bishop::of, EnumTeam.BLACK, EnumPosition.from("F8")),
    BLACK_KNIGHT_2(Knight::of, EnumTeam.BLACK, EnumPosition.from("G8")),
    BLACK_ROOK_2(Rook::of, EnumTeam.BLACK, EnumPosition.from("H8")),
    WHITE_PAWN_1(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("A2")),
    WHITE_PAWN_2(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("B2")),
    WHITE_PAWN_3(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("C2")),
    WHITE_PAWN_4(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("D2")),
    WHITE_PAWN_5(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("E2")),
    WHITE_PAWN_6(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("F2")),
    WHITE_PAWN_7(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("G2")),
    WHITE_PAWN_8(NotMovedPawn::of, EnumTeam.WHITE, EnumPosition.from("H2")),
    WHITE_ROOK_1(Rook::of, EnumTeam.WHITE, EnumPosition.from("A1")),
    WHITE_KNIGHT_1(Knight::of, EnumTeam.WHITE, EnumPosition.from("B1")),
    WHITE_BISHOP_1(Bishop::of, EnumTeam.WHITE, EnumPosition.from("C1")),
    WHITE_QUEEN(Queen::of, EnumTeam.WHITE, EnumPosition.from("D1")),
    WHITE_KING(King::of, EnumTeam.WHITE, EnumPosition.from("E1")),
    WHITE_BISHOP_2(Bishop::of, EnumTeam.WHITE, EnumPosition.from("F1")),
    WHITE_KNIGHT_2(Knight::of, EnumTeam.WHITE, EnumPosition.from("G1")),
    WHITE_ROOK_2(Rook::of, EnumTeam.WHITE, EnumPosition.from("H1"));

    private BiFunction<Position, Team, PieceState> generator;
    private Team team;
    private Position position;

    InitialPieceRepository(BiFunction<Position, Team, PieceState> generator, Team team, Position position) {
        this.generator = generator;
        this.team = team;
        this.position = position;
    }

    public PieceState getInitialPiece() {
        return generator.apply(position, team);
    }

    public Position getPosition() {
        return position;
    }
}
