package chess.domain.board;

import chess.domain.Player;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoard implements Observer {
    private Map<Position, Piece> pieces;

    /*
    TODO: 2020/03/21 그림해독기
    "♖♖♖♖♖♖♖♖"         7,7
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
    "♖♖♖♖♖♖♖♖"
     0,0               7,0
     */
    public ChessBoard() {
        pieces = Stream.of(
                Pawn.of(Position.of(0, 0), Player.WHITE, this),
                Pawn.of(Position.of(1, 0), Player.WHITE, this),
                Pawn.of(Position.of(2, 0), Player.WHITE, this),
                Pawn.of(Position.of(3, 0), Player.WHITE, this),
                Pawn.of(Position.of(4, 0), Player.WHITE, this),
                Pawn.of(Position.of(5, 0), Player.WHITE, this),
                Pawn.of(Position.of(6, 0), Player.WHITE, this),
                Pawn.of(Position.of(7, 0), Player.WHITE, this),
                Pawn.of(Position.of(0, 1), Player.WHITE, this),
                Pawn.of(Position.of(1, 1), Player.WHITE, this),
                Pawn.of(Position.of(2, 1), Player.WHITE, this),
                Pawn.of(Position.of(3, 1), Player.WHITE, this),
                Pawn.of(Position.of(4, 1), Player.WHITE, this),
                Pawn.of(Position.of(5, 1), Player.WHITE, this),
                Pawn.of(Position.of(6, 1), Player.WHITE, this),
                Pawn.of(Position.of(7, 1), Player.WHITE, this)
        ).collect(Collectors.toMap(Piece::getPosition, piece -> piece));
    }

    public void move(Position source, Position destination) {
        try {
            Piece piece = pieces.get(source);
            piece.move(destination, toDto());
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    private ChessBoardDto toDto() {
        return new ChessBoardDto();
    }

    @Override
    public void update(Observable observable, Object o) {
        Piece piece = (Piece) observable;
        pieces.remove(piece);
        pieces.put(piece.getPosition(), piece);
    }
}
