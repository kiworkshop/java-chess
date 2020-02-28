package mychess.domain;

import mychess.domain.piece.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Board {

    private Map<Position, Piece> pieces;

    public Board() {
        initialize();
    }

    private void initialize() {
        pieces = new EnumMap<>(Position.class);
        Arrays.stream(Position.values()).forEach(position -> pieces.put(position, initialPiece(position)));
    }

    private Piece initialPiece(Position position) {
        if(position.isFrontLineOfBlack()) return new Pawn();
        if(position.isBackLineOfBlack()) return initialBlackPiece(position);
        if(position.isFrontLineOfWhite()) return new Pawn();
        if(position.isBackLineOfWhite()) return initialWhitePiece(position);
        return new EmptyPiece();
    }

    private Piece initialBlackPiece(Position position) {
        if (position == Position.A8) return new Rook();
        if (position == Position.B8) return new Knight();
        if (position == Position.C8) return new Bishop();
        if (position == Position.D8) return new Queen();
        if (position == Position.E8) return new King();
        if (position == Position.F8) return new Bishop();
        if (position == Position.G8) return new Knight();
        if (position == Position.H8) return new Rook();
        throw new IllegalStateException("Unexpected value: " + position);
    }

    private Piece initialWhitePiece(Position position) {
        if (position == Position.A1) return new Rook();
        if (position == Position.B1) return new Knight();
        if (position == Position.C1) return new Bishop();
        if (position == Position.D1) return new Queen();
        if (position == Position.E1) return new King();
        if (position == Position.F1) return new Bishop();
        if (position == Position.G1) return new Knight();
        if (position == Position.H1) return new Rook();
        throw new IllegalStateException("Unexpected value: " + position);
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
