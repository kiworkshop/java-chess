package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {

    private final Map<Position, Piece> pieces;
    private final AttackPositions attackPositions;

    public Player(final Color color) {
        pieces = new HashMap<>(PieceFactory.createPieces(color));
        attackPositions = new AttackPositions(pieces);
    }

    public boolean hasPieceOn(final Position position) {
        return pieces.containsKey(position);
    }

    public Set<Position> findPaths(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        return sourcePiece.findPath(source, target);
    }

    public void update(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        movePiece(source, target, sourcePiece);
        attackPositions.update(source, target, sourcePiece);
    }

    private void movePiece(final Position source, final Position target, final Piece sourcePiece) {
        pieces.put(target, sourcePiece);
        pieces.remove(source);
    }

    public Piece findPieceBy(final Position position) {
        if (!hasPieceOn(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return pieces.get(position);
    }
}
