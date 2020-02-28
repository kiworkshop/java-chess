package mychess.domain;

import mychess.domain.piece.EmptyPiece;
import mychess.domain.piece.Pawn;
import mychess.domain.piece.Piece;

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
        switch (position) {
            case A1: return new Rook();
            case A2: return new Knight();
            case A3: return new Bishop();
            case A4: return new Queen();
            case A5: return new King();
            case A6: return new Bishop();
            case A7: return new Knight();
            case A8: return new Rook();
        }
    }

    private Piece initialWhitePiece(Position position) {
        return new Pawn();
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
