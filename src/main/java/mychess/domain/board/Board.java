package mychess.domain.board;

import mychess.domain.piece.*;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board implements Observer {

    private Map<Position, Piece> pieces = new TreeMap<>();

    public Board() {
        initializeBlackPieces();
        initializeWhitePieces();
        initializeNullPieces();
    }

    private void initializeWhitePieces() {
        Map<Position, Piece> whitePieces = Stream.of(
                new Rook(Position.of(0, 0), Player.WHITE, this),
                new Knight(Position.of(1, 0), Player.WHITE, this),
                new Bishop(Position.of(2, 0), Player.WHITE, this),
                new Queen(Position.of(3, 0), Player.WHITE, this),
                new King(Position.of(4, 0), Player.WHITE, this),
                new Bishop(Position.of(5, 0), Player.WHITE, this),
                new Knight(Position.of(6, 0), Player.WHITE, this),
                new Rook(Position.of(7, 0), Player.WHITE, this),
                new Pawn(Position.of(0, 1), Player.WHITE, this),
                new Pawn(Position.of(1, 1), Player.WHITE, this),
                new Pawn(Position.of(2, 1), Player.WHITE, this),
                new Pawn(Position.of(3, 1), Player.WHITE, this),
                new Pawn(Position.of(4, 1), Player.WHITE, this),
                new Pawn(Position.of(5, 1), Player.WHITE, this),
                new Pawn(Position.of(6, 1), Player.WHITE, this),
                new Pawn(Position.of(7, 1), Player.WHITE, this)
        ).collect(Collectors.toMap(Piece::getPosition, piece -> piece));

        pieces.putAll(whitePieces);
    }

    private void initializeBlackPieces() {
        Map<Position, Piece> blackPieces = Stream.of(
                new Rook(Position.of(0, 7), Player.BLACK, this),
                new Knight(Position.of(1, 7), Player.BLACK, this),
                new Bishop(Position.of(2, 7), Player.BLACK, this),
                new Queen(Position.of(3, 7), Player.BLACK, this),
                new King(Position.of(4, 7), Player.BLACK, this),
                new Bishop(Position.of(5, 7), Player.BLACK, this),
                new Knight(Position.of(6, 7), Player.BLACK, this),
                new Rook(Position.of(7, 7), Player.BLACK, this),
                new Pawn(Position.of(0, 6), Player.BLACK, this),
                new Pawn(Position.of(1, 6), Player.BLACK, this),
                new Pawn(Position.of(2, 6), Player.BLACK, this),
                new Pawn(Position.of(3, 6), Player.BLACK, this),
                new Pawn(Position.of(4, 6), Player.BLACK, this),
                new Pawn(Position.of(5, 6), Player.BLACK, this),
                new Pawn(Position.of(6, 6), Player.BLACK, this),
                new Pawn(Position.of(7, 6), Player.BLACK, this)
        ).collect(Collectors.toMap(Piece::getPosition, piece -> piece));

        pieces.putAll(blackPieces);
    }

    private void initializeNullPieces() {
        Map<Position, Piece> nullPieces = Stream.of(
                new NullPiece(Position.of(0, 2), Player.NONE, this),
                new NullPiece(Position.of(1, 2), Player.NONE, this),
                new NullPiece(Position.of(2, 2), Player.NONE, this),
                new NullPiece(Position.of(3, 2), Player.NONE, this),
                new NullPiece(Position.of(4, 2), Player.NONE, this),
                new NullPiece(Position.of(5, 2), Player.NONE, this),
                new NullPiece(Position.of(6, 2), Player.NONE, this),
                new NullPiece(Position.of(7, 2), Player.NONE, this),
                new NullPiece(Position.of(0, 3), Player.NONE, this),
                new NullPiece(Position.of(1, 3), Player.NONE, this),
                new NullPiece(Position.of(2, 3), Player.NONE, this),
                new NullPiece(Position.of(3, 3), Player.NONE, this),
                new NullPiece(Position.of(4, 3), Player.NONE, this),
                new NullPiece(Position.of(5, 3), Player.NONE, this),
                new NullPiece(Position.of(6, 3), Player.NONE, this),
                new NullPiece(Position.of(7, 3), Player.NONE, this),
                new NullPiece(Position.of(0, 4), Player.NONE, this),
                new NullPiece(Position.of(1, 4), Player.NONE, this),
                new NullPiece(Position.of(2, 4), Player.NONE, this),
                new NullPiece(Position.of(3, 4), Player.NONE, this),
                new NullPiece(Position.of(4, 4), Player.NONE, this),
                new NullPiece(Position.of(5, 4), Player.NONE, this),
                new NullPiece(Position.of(6, 4), Player.NONE, this),
                new NullPiece(Position.of(7, 4), Player.NONE, this),
                new NullPiece(Position.of(0, 5), Player.NONE, this),
                new NullPiece(Position.of(1, 5), Player.NONE, this),
                new NullPiece(Position.of(2, 5), Player.NONE, this),
                new NullPiece(Position.of(3, 5), Player.NONE, this),
                new NullPiece(Position.of(4, 5), Player.NONE, this),
                new NullPiece(Position.of(5, 5), Player.NONE, this),
                new NullPiece(Position.of(6, 5), Player.NONE, this),
                new NullPiece(Position.of(7, 5), Player.NONE, this)
        ).collect(Collectors.toMap(Piece::getPosition, piece -> piece));

        pieces.putAll(nullPieces);
    }

    public void move(Position source, Position destination) {
        try {
            Piece piece = pieces.get(source);
            piece.move(destination, toDto());
            pieces.put(piece.getPosition(), piece);

            NullPiece nullPiece = new NullPiece(source, Player.NONE,this);
            pieces.put(source, nullPiece);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    private BoardDto toDto() {
        return new BoardDto();
    }

    // update가 동작하지 않아서 move에 같은 코드를 추가해놓았다ㅠㅠ 왜 그럴까
    @Override
    public void update(Observable observable, Object o) {
        Piece piece = (Piece) observable;
        pieces.put(piece.getPosition(), piece);
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
