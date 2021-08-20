package chess.domain.player;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.mapper.PawnMapper;
import chess.domain.piece.mapper.PieceMappers;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private final Map<Position, Piece> pieces;
    private final AttackPositions attackPositions;

    public Player(final Color color) {
        pieces = new HashMap<>(PieceFactory.createPieces(color));
        attackPositions = new AttackPositions(pieces);
    }

    public double sumScores() {
        List<Position> pawnPositions = pieces.keySet()
                .stream()
                .filter(position -> {
                    Piece piece = pieces.get(position);
                    return piece.isPawn();
                })
                .collect(Collectors.toList());

        double pawnScores = calculatePawnScores(pawnPositions);
        double sum = pieces.values()
                .stream()
                .filter(piece -> !piece.isPawn())
                .mapToDouble(PieceMappers::findScoreBy)
                .sum();
        return pawnScores + sum;
    }

    private double calculatePawnScores(final List<Position> pawnPositions) {
        Map<File, Integer> pawnCount = new EnumMap<>(File.class);

        pawnPositions.stream()
                .map(Position::getFile)
                .forEach(file -> pawnCount.put(file, pawnCount.getOrDefault(file, 0) + 1));

        return pawnCount.values()
                .stream()
                .mapToDouble(PawnMapper::calculate)
                .sum();
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

    public boolean hasKingOn(Position position) {
        return findPieceBy(position).isKing();
    }

    public boolean isKingDead() {
        return pieces.values().stream()
                .noneMatch(Piece::isKing);
    }

    public boolean canAttack(Position position) {
        return attackPositions.contains(position);
    }

    public void attacked(final Position target) {
        if (!hasPieceOn(target)) {
            return;
        }

        attackPositions.remove(target, pieces.get(target));
        pieces.remove(target);
    }
}
