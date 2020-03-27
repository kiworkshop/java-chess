package mychess.domain;

import mychess.domain.piece.*;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

import java.util.Map;
import java.util.TreeMap;

public class Board {

    public static final int BOARD_SIZE = 8;

    private Map<Position, Piece> pieces = new TreeMap<>();

    public Board() {
        initializeBlackPieces();
        initializeWhitePieces();
        initializeEmptyPieces();
    }

    private void initializeWhitePieces() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            pieces.put(Position.of(x, 1), new Pawn(Color.WHITE));
        }

        pieces.put(Position.of(0, 0), new Rook(Color.WHITE));
        pieces.put(Position.of(1, 0), new Knight(Color.WHITE));
        pieces.put(Position.of(2, 0), new Bishop(Color.WHITE));
        pieces.put(Position.of(3, 0), new Queen(Color.WHITE));
        pieces.put(Position.of(4, 0), new King(Color.WHITE));
        pieces.put(Position.of(5, 0), new Bishop(Color.WHITE));
        pieces.put(Position.of(6, 0), new Knight(Color.WHITE));
        pieces.put(Position.of(7, 0), new Rook(Color.WHITE));
    }

    private void initializeBlackPieces() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            pieces.put(Position.of(x, 6), new Pawn(Color.BLACK));
        }

        pieces.put(Position.of(0, 7), new Rook(Color.BLACK));
        pieces.put(Position.of(1, 7), new Knight(Color.BLACK));
        pieces.put(Position.of(2, 7), new Bishop(Color.BLACK));
        pieces.put(Position.of(3, 7), new Queen(Color.BLACK));
        pieces.put(Position.of(4, 7), new King(Color.BLACK));
        pieces.put(Position.of(5, 7), new Bishop(Color.BLACK));
        pieces.put(Position.of(6, 7), new Knight(Color.BLACK));
        pieces.put(Position.of(7, 7), new Rook(Color.BLACK));
    }

    private void initializeEmptyPieces() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 2; y <= 5; y++) {
                pieces.put(Position.of(x, y), new EmptyPiece());
            }
        }
    }

    public void movePiece(Player player, Position source, Position destination) {
        Piece sourcePiece = pieces.get(source);
        Piece destinationPiece = pieces.get(destination);
        if (isValidPiece(sourcePiece)
                && hasSameColor(player, sourcePiece)
                && !hasSameColor(player, destinationPiece)) {
            pieces.put(destination, sourcePiece);
            pieces.put(source, new EmptyPiece());
            return;
        }
        throw new IllegalArgumentException();
    }

    private boolean isValidPiece(Piece sourcePiece) {
        return sourcePiece.getClass() != EmptyPiece.class;
    }

    private boolean hasSameColor(Player player, Piece sourcePiece) {
        return player.getColor().equals(sourcePiece.getColor());
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
