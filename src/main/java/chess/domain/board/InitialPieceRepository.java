package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

public enum InitialPieceRepository {
    ROOK_WHITE_A1(Position.of("a1"), Rook.of(Team.WHITE, Position.of("a1"))),
    KNIGHT_WHITE_B1(Position.of("b1"), Knight.of(Team.WHITE, Position.of("b1"))),
    BISHOP_WHITE_C1(Position.of("c1"), Bishop.of(Team.WHITE, Position.of("c1"))),
    QUEEN_WHITE_D1(Position.of("d1"), Queen.of(Team.WHITE, Position.of("d1"))),
    KING_WHITE_E1(Position.of("e1"), King.of(Team.WHITE, Position.of("e1"))),
    BISHOP_WHITE_F1(Position.of("f1"), Bishop.of(Team.WHITE, Position.of("f1"))),
    KNIGHT_WHITE_G1(Position.of("g1"), Knight.of(Team.WHITE, Position.of("g1"))),
    ROOK_WHITE_H1(Position.of("h1"), Rook.of(Team.WHITE, Position.of("h1"))),

    PAWN_WHITE_A2(Position.of("a2"), Pawn.of(Team.WHITE, Position.of("a2"))),
    PAWN_WHITE_B2(Position.of("b2"), Pawn.of(Team.WHITE, Position.of("b2"))),
    PAWN_WHITE_C2(Position.of("c2"), Pawn.of(Team.WHITE, Position.of("c2"))),
    PAWN_WHITE_D2(Position.of("d2"), Pawn.of(Team.WHITE, Position.of("d2"))),
    PAWN_WHITE_E2(Position.of("e2"), Pawn.of(Team.WHITE, Position.of("e2"))),
    PAWN_WHITE_F2(Position.of("f2"), Pawn.of(Team.WHITE, Position.of("f2"))),
    PAWN_WHITE_G2(Position.of("g2"), Pawn.of(Team.WHITE, Position.of("g2"))),
    PAWN_WHITE_H2(Position.of("h2"), Pawn.of(Team.WHITE, Position.of("h2"))),

    ROOK_BLACK_A8(Position.of("a8"), Rook.of(Team.BLACK, Position.of("a8"))),
    KNIGHT_BLACK_B8(Position.of("b8"), Knight.of(Team.BLACK, Position.of("b8"))),
    BISHOP_BLACK_C8(Position.of("c8"), Bishop.of(Team.BLACK, Position.of("c8"))),
    QUEEN_BLACK_D8(Position.of("d8"), Queen.of(Team.BLACK, Position.of("d8"))),
    KING_BLACK_E8(Position.of("e8"), King.of(Team.BLACK, Position.of("e8"))),
    BISHOP_BLACK_F8(Position.of("f8"), Bishop.of(Team.BLACK, Position.of("f8"))),
    KNIGHT_BLACK_G8(Position.of("g8"), Knight.of(Team.BLACK, Position.of("g8"))),
    ROOK_BLACK_H8(Position.of("h8"), Rook.of(Team.BLACK, Position.of("h8"))),

    PAWN_BLACK_A7(Position.of("a7"), Pawn.of(Team.BLACK, Position.of("a7"))),
    PAWN_BLACK_B7(Position.of("b7"), Pawn.of(Team.BLACK, Position.of("b7"))),
    PAWN_BLACK_C7(Position.of("c7"), Pawn.of(Team.BLACK, Position.of("c7"))),
    PAWN_BLACK_D7(Position.of("d7"), Pawn.of(Team.BLACK, Position.of("d7"))),
    PAWN_BLACK_E7(Position.of("e7"), Pawn.of(Team.BLACK, Position.of("e7"))),
    PAWN_BLACK_F7(Position.of("f7"), Pawn.of(Team.BLACK, Position.of("f7"))),
    PAWN_BLACK_G7(Position.of("g7"), Pawn.of(Team.BLACK, Position.of("g7"))),
    PAWN_BLACK_H7(Position.of("h7"), Pawn.of(Team.BLACK, Position.of("h7")));

    private final Position position;
    private final Piece piece;

    InitialPieceRepository(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position position() {
        return position;
    }

    public Piece object() {
        return piece;
    }
}
