package chess.domain.player;

import chess.domain.board.File;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceResolver;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Player {

    private final Map<Position, Piece> pieces;
    private final AttackRange attackRange;

    public Player(final Color color) {
        pieces = new HashMap<>(PieceFactory.createPieces(color));
        attackRange = new AttackRange(pieces);
    }

    public double sumScores() {
        double pawnScores = calculatePawnScores();
        double scoresExceptPawn = calculateScoresExceptPawn();

        return pawnScores + scoresExceptPawn;
    }

    private double calculatePawnScores() {
        List<Position> pawnPositions = findPawnPositions();
        Map<File, Integer> pawnCountsOnSameFile = extractPawnCountsOnSameFile(pawnPositions);

        return pawnCountsOnSameFile.values()
                .stream()
                .mapToDouble(PieceResolver::sumPawnScores)
                .sum();
    }

    private List<Position> findPawnPositions() {
        return pieces.keySet()
                .stream()
                .filter(position -> {
                    Piece piece = pieces.get(position);
                    return piece.isPawn();
                })
                .collect(Collectors.toList());
    }

    private Map<File, Integer> extractPawnCountsOnSameFile(final List<Position> pawnPositions) {
        Map<File, Integer> pawnCountOnSameFile = new EnumMap<>(File.class);

        pawnPositions.stream()
                .map(Position::getFile)
                .forEach(file -> pawnCountOnSameFile.put(file, pawnCountOnSameFile.getOrDefault(file, 0) + 1));

        return pawnCountOnSameFile;
    }

    private double calculateScoresExceptPawn() {
        return pieces.values()
                .stream()
                .filter(Piece::isNotPawn)
                .mapToDouble(PieceResolver::findScoreBy)
                .sum();
    }

    public Collection<Position> findPaths(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);

        return sourcePiece.findPath(source, target);
    }

    public Piece findPieceBy(final Position position) {
        if (isEmptyOn(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return pieces.get(position);
    }

    private boolean isEmptyOn(final Position position) {
        return !pieces.containsKey(position);
    }

    public void update(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        movePiece(source, target, sourcePiece);
        attackRange.update(source, target, sourcePiece);
    }

    private void movePiece(final Position source, final Position target, final Piece sourcePiece) {
        pieces.put(target, sourcePiece);
        pieces.remove(source);
    }

    public boolean hasKingOn(final Position position) {
        Piece piece = findPieceBy(position);

        return piece.isKing();
    }

    public boolean isKingDead() {
        return pieces.values().stream()
                .noneMatch(Piece::isKing);
    }

    public boolean canAttack(final Position position) {
        return attackRange.contains(position);
    }

    public void removePieceOn(final Position position) {
        if (isEmptyOn(position)) {
            return;
        }

        attackRange.remove(position, pieces.get(position));
        pieces.remove(position);
    }

    public boolean hasPieceOn(final Position position) {
        return pieces.containsKey(position);
    }
}
