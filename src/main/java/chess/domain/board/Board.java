package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Board {
    public static Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        validateSource(sourcePiece);
        sourcePiece.move(target);

        board.remove(source);
        board.put(target, sourcePiece);
    }

    private void validateSource(Piece sourcePiece) {
        if (sourcePiece.team().equals(Team.NEUTRAL)) {
            throw new IllegalArgumentException("지정 위치에 체스말이 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece from(Position position) {
        return board.get(position);
    }
}
