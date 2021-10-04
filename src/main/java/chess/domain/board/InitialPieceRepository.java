package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class InitialPieceRepository {
    private InitialPieceRepository() {
    }

    public static Map<Position, Piece> init(Map<String, Position> positions) {
        Map<Position, Piece> pieceMap = new LinkedHashMap<>();
        pieceMap.put(positions.get("a1"), Rook.of(Team.WHITE, positions.get("a1")));
        pieceMap.put(positions.get("b1"), Knight.of(Team.WHITE, positions.get("b1")));
        pieceMap.put(positions.get("c1"), Bishop.of(Team.WHITE, positions.get("c1")));
        pieceMap.put(positions.get("d1"), Queen.of(Team.WHITE, positions.get("d1")));
        pieceMap.put(positions.get("e1"), King.of(Team.WHITE, positions.get("e1")));
        pieceMap.put(positions.get("f1"), Bishop.of(Team.WHITE, positions.get("f1")));
        pieceMap.put(positions.get("g1"), Knight.of(Team.WHITE, positions.get("g1")));
        pieceMap.put(positions.get("h1"), Rook.of(Team.WHITE, positions.get("h1")));
        pieceMap.put(positions.get("a2"), Pawn.of(Team.WHITE, positions.get("a2")));
        pieceMap.put(positions.get("b2"), Pawn.of(Team.WHITE, positions.get("b2")));
        pieceMap.put(positions.get("c2"), Pawn.of(Team.WHITE, positions.get("c2")));
        pieceMap.put(positions.get("d2"), Pawn.of(Team.WHITE, positions.get("d2")));
        pieceMap.put(positions.get("e2"), Pawn.of(Team.WHITE, positions.get("e2")));
        pieceMap.put(positions.get("f2"), Pawn.of(Team.WHITE, positions.get("f2")));
        pieceMap.put(positions.get("g2"), Pawn.of(Team.WHITE, positions.get("g2")));
        pieceMap.put(positions.get("h2"), Pawn.of(Team.WHITE, positions.get("h2")));
        pieceMap.put(positions.get("a8"), Rook.of(Team.BLACK, positions.get("a8")));
        pieceMap.put(positions.get("b8"), Knight.of(Team.BLACK, positions.get("b8")));
        pieceMap.put(positions.get("c8"), Bishop.of(Team.BLACK, positions.get("c8")));
        pieceMap.put(positions.get("d8"), Queen.of(Team.BLACK, positions.get("d8")));
        pieceMap.put(positions.get("e8"), King.of(Team.BLACK, positions.get("e8")));
        pieceMap.put(positions.get("f8"), Bishop.of(Team.BLACK, positions.get("f8")));
        pieceMap.put(positions.get("g8"), Knight.of(Team.BLACK, positions.get("g8")));
        pieceMap.put(positions.get("h8"), Rook.of(Team.BLACK, positions.get("h8")));
        pieceMap.put(positions.get("a7"), Pawn.of(Team.BLACK, positions.get("a7")));
        pieceMap.put(positions.get("b7"), Pawn.of(Team.BLACK, positions.get("b7")));
        pieceMap.put(positions.get("c7"), Pawn.of(Team.BLACK, positions.get("c7")));
        pieceMap.put(positions.get("d7"), Pawn.of(Team.BLACK, positions.get("d7")));
        pieceMap.put(positions.get("e7"), Pawn.of(Team.BLACK, positions.get("e7")));
        pieceMap.put(positions.get("f7"), Pawn.of(Team.BLACK, positions.get("f7")));
        pieceMap.put(positions.get("g7"), Pawn.of(Team.BLACK, positions.get("g7")));
        pieceMap.put(positions.get("h7"), Pawn.of(Team.BLACK, positions.get("h7")));
        return pieceMap;
    }
}
