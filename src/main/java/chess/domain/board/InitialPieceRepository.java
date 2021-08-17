package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

public enum InitialPieceRepository {
    ROOK_WHITE_A1(Position.from("a1"), Rook.of(Team.WHITE, Position.from("a1"))),
    KNIGHT_WHITE_B1(Position.from("b1"), Knight.of(Team.WHITE, Position.from("b1"))),
    BISHOP_WHITE_C1(Position.from("c1"), Bishop.of(Team.WHITE, Position.from("c1"))),
    QUEEN_WHITE_D1(Position.from("d1"), Queen.of(Team.WHITE, Position.from("d1"))),
    KING_WHITE_E1(Position.from("e1"), King.of(Team.WHITE, Position.from("e1"))),
    BISHOP_WHITE_F1(Position.from("f1"), Bishop.of(Team.WHITE, Position.from("f1"))),
    KNIGHT_WHITE_G1(Position.from("g1"), Knight.of(Team.WHITE, Position.from("g1"))),
    ROOK_WHITE_H1(Position.from("h1"), Rook.of(Team.WHITE, Position.from("h1"))),

    PAWN_WHITE_A2(Position.from("a2"), Pawn.of(Team.WHITE, Position.from("a2"))),
    PAWN_WHITE_B2(Position.from("b2"), Pawn.of(Team.WHITE, Position.from("b2"))),
    PAWN_WHITE_C2(Position.from("c2"), Pawn.of(Team.WHITE, Position.from("c2"))),
    PAWN_WHITE_D2(Position.from("d2"), Pawn.of(Team.WHITE, Position.from("d2"))),
    PAWN_WHITE_E2(Position.from("e2"), Pawn.of(Team.WHITE, Position.from("e2"))),
    PAWN_WHITE_F2(Position.from("f2"), Pawn.of(Team.WHITE, Position.from("f2"))),
    PAWN_WHITE_G2(Position.from("g2"), Pawn.of(Team.WHITE, Position.from("g2"))),
    PAWN_WHITE_H2(Position.from("h2"), Pawn.of(Team.WHITE, Position.from("h2"))),

    ROOK_BLACK_A8(Position.from("a8"), Rook.of(Team.BLACK, Position.from("a8"))),
    KNIGHT_BLACK_B8(Position.from("b8"), Knight.of(Team.BLACK, Position.from("b8"))),
    BISHOP_BLACK_C8(Position.from("c8"), Bishop.of(Team.BLACK, Position.from("c8"))),
    QUEEN_BLACK_D8(Position.from("d8"), Queen.of(Team.BLACK, Position.from("d8"))),
    KING_BLACK_E8(Position.from("e8"), King.of(Team.BLACK, Position.from("e8"))),
    BISHOP_BLACK_F8(Position.from("f8"), Bishop.of(Team.BLACK, Position.from("f8"))),
    KNIGHT_BLACK_G8(Position.from("g8"), Knight.of(Team.BLACK, Position.from("g8"))),
    ROOK_BLACK_H8(Position.from("h8"), Rook.of(Team.BLACK, Position.from("h8"))),

    PAWN_BLACK_A7(Position.from("a7"), Pawn.of(Team.BLACK, Position.from("a7"))),
    PAWN_BLACK_B7(Position.from("b7"), Pawn.of(Team.BLACK, Position.from("b7"))),
    PAWN_BLACK_C7(Position.from("c7"), Pawn.of(Team.BLACK, Position.from("c7"))),
    PAWN_BLACK_D7(Position.from("d7"), Pawn.of(Team.BLACK, Position.from("d7"))),
    PAWN_BLACK_E7(Position.from("e7"), Pawn.of(Team.BLACK, Position.from("e7"))),
    PAWN_BLACK_F7(Position.from("f7"), Pawn.of(Team.BLACK, Position.from("f7"))),
    PAWN_BLACK_G7(Position.from("g7"), Pawn.of(Team.BLACK, Position.from("g7"))),
    PAWN_BLACK_H7(Position.from("h7"), Pawn.of(Team.BLACK, Position.from("h7")));

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
