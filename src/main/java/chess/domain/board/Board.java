package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class Board {
    public Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (validateSource(sourcePiece)) {
            sourcePiece.move(target);
        }
        board.remove(source);
        board.put(target, sourcePiece);
    }

    private boolean validateSource(Piece sourcePiece) {
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
        return true;
    }

    public Piece from(Position position) {
        return board.get(position);
    }
}
