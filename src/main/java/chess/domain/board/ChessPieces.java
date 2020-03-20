package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Movable;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.Arrays;

public enum ChessPieces {

    BLACK_ROOK_1(Rook.from(false), Position.from("A1")),
    BLACK_KNIGHT_1(Knight.from(false), Position.from("B1")),
    BLACK_BISHOP_1(Bishop.from(false), Position.from("C1")),
    BLACK_KING(King.from(false), Position.from("D1")),
    BLACK_QUEEN(Queen.from(false), Position.from("E1")),
    BLACK_BISHOP_2(Bishop.from(false), Position.from("F1")),
    BLACK_KNIGHT_2(Knight.from(false), Position.from("G1")),
    BLACK_ROOK_2(Rook.from(false), Position.from("H1")),
    BLACK_PAWN_1(Pawn.from(false), Position.from("A2")),
    BLACK_PAWN_2(Pawn.from(false), Position.from("B2")),
    BLACK_PAWN_3(Pawn.from(false), Position.from("C2")),
    BLACK_PAWN_4(Pawn.from(false), Position.from("D2")),
    BLACK_PAWN_5(Pawn.from(false), Position.from("E2")),
    BLACK_PAWN_6(Pawn.from(false), Position.from("F2")),
    BLACK_PAWN_7(Pawn.from(false), Position.from("G2")),
    BLACK_PAWN_8(Pawn.from(false), Position.from("H2")),
    WHITE_PAWN_1(Pawn.from(true), Position.from("A7")),
    WHITE_PAWN_2(Pawn.from(true), Position.from("B7")),
    WHITE_PAWN_3(Pawn.from(true), Position.from("C7")),
    WHITE_PAWN_4(Pawn.from(true), Position.from("D7")),
    WHITE_PAWN_5(Pawn.from(true), Position.from("E7")),
    WHITE_PAWN_6(Pawn.from(true), Position.from("F7")),
    WHITE_PAWN_7(Pawn.from(true), Position.from("G7")),
    WHITE_PAWN_8(Pawn.from(true), Position.from("H7")),
    WHITE_ROOK_1(Rook.from(true), Position.from("A8")),
    WHITE_KNIGHT_1(Knight.from(true), Position.from("B8")),
    WHITE_BISHOP_1(Bishop.from(true), Position.from("C8")),
    WHITE_KING(King.from(true), Position.from("D8")),
    WHITE_QUEEN(Queen.from(true), Position.from("E8")),
    WHITE_BISHOP_2(Bishop.from(true), Position.from("F8")),
    WHITE_KNIGHT_2(Knight.from(true), Position.from("G8")),
    WHITE_ROOK_2(Rook.from(true), Position.from("H8"));

    private Movable movable;
    private Position startingPosition;

    ChessPieces(Movable movable, Position startingPosition) {
        this.movable = movable;
        this.startingPosition = startingPosition;
    }

    public static Movable getMovable(Position position) {
        return Arrays.stream(ChessPieces.values())
                .filter(piece -> piece.startingPosition == position)
                .findFirst()
                .map(piece -> piece.movable)
                .orElse(Empty.create());
    }

}
