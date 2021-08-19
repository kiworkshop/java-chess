package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {

    private final Map<Position, Piece> board;

    public Player(final Color color) {
        board = new HashMap<>(PieceFactory.createPieces(color));
    }

    public boolean hasPieceOn(final Position position) {
        return board.containsKey(position);
    }

    public Set<Position> findPaths(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        return sourcePiece.findPaths(source, target);
    }

    public void update(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        board.put(target, sourcePiece);
        board.remove(source);
    }

    public Piece findPieceBy(final Position position) {
        if (!hasPieceOn(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return board.get(position);
    }
}
