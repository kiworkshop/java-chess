package mychess.domain;

//import mychess.domain.command.Command;
import mychess.controller.dto.MoveParams;
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
        if(position.isFrontLineOfBlack()) return new Pawn(false);
        if(position.isBackLineOfBlack()) return initialBlackPiece(position);
        if(position.isFrontLineOfWhite()) return new Pawn(true);
        if(position.isBackLineOfWhite()) return initialWhitePiece(position);
        return new EmptyPiece();
    }

    private Piece initialBlackPiece(Position position) {
        if (position == Position.A8) return new Rook(false);
        if (position == Position.B8) return new Knight(false);
        if (position == Position.C8) return new Bishop(false);
        if (position == Position.D8) return new Queen(false);
        if (position == Position.E8) return new King(false);
        if (position == Position.F8) return new Bishop(false);
        if (position == Position.G8) return new Knight(false);
        if (position == Position.H8) return new Rook(false);
        throw new IllegalStateException("Unexpected value: " + position);
    }

    private Piece initialWhitePiece(Position position) {
        if (position == Position.A1) return new Rook(true);
        if (position == Position.B1) return new Knight(true);
        if (position == Position.C1) return new Bishop(true);
        if (position == Position.D1) return new Queen(true);
        if (position == Position.E1) return new King(true);
        if (position == Position.F1) return new Bishop(true);
        if (position == Position.G1) return new Knight(true);
        if (position == Position.H1) return new Rook(true);
        throw new IllegalStateException("Unexpected value: " + position);
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    public void movePiece(MoveParams params) {
        Position source = Position.toPosition(params.getSource());
        Position destination = Position.toPosition(params.getDestination());

        Piece sourcePiece = pieces.get(source);

        pieces.put(destination, sourcePiece);
        pieces.put(source, new EmptyPiece());
    }
}
