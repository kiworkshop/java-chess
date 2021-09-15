package chess.domain.team;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.PieceFactory;
import chess.domain.piece.type.PieceType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Team {

    private final Map<Position, Piece> pieces;

    public static Team white() {
        return new Team(Color.WHITE);
    }

    public static Team black() {
        return new Team(Color.BLACK);
    }

    private Team(final Color color) {
        pieces = new HashMap<>(PieceFactory.initialPieces(color));
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }

    public Path findMovePath(final Position source, final Position target) {
        Piece sourcePiece = findPieceBy(source);
        return sourcePiece.findMovePath(source, target);
    }

    public Collection<Path> findAttackPaths(final Position target) {
        return pieces.entrySet().stream()
                .map(entry -> entry.getValue().findAttackPaths(entry.getKey()))
                .flatMap(Collection::stream)
                .filter(path -> path.contains(target))
                .map(path -> path.getPositionsUntilTarget(target))
                .filter(Path::isNotEmpty)
                .collect(Collectors.toList());
    }

    public void wasAttackedBy(final Position position) {
        if (hasNoPieceOn(position)) {
            return;
        }

        pieces.remove(position);
    }

    public Piece findPieceBy(final Position position) {
        if (hasNoPieceOn(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return pieces.get(position);
    }

    public boolean hasPieceOn(final Position position) {
        return pieces.containsKey(position);
    }

    public boolean hasNoPieceOn(final Position position) {
        return !hasPieceOn(position);
    }

    public boolean hasKingOn(final Position position) {
        Piece piece = findPieceBy(position);
        return piece.isKing();
    }

    private boolean hasPawnOn(final Position position) {
        Piece piece = findPieceBy(position);
        return piece.isPawn();
    }

    public boolean isKingDead() {
        return pieces.values().stream()
                .noneMatch(Piece::isKing);
    }

    public boolean isKingAlive() {
        return pieces.values().stream()
                .anyMatch(Piece::isKing);
    }

    public boolean isPawnAttacking(final Position source, final Position target) {
        Piece piece = findPieceBy(source);
        if (piece.isNotPawn()) {
            return false;
        }

        Pawn pawn = (Pawn) piece;
        return pawn.isAttacking(source, target);
    }

    public double calculateScores() {
        double pawnScores = calculatePawnScores();
        double scoresExceptPawn = calculateScoresExceptPawn();

        return pawnScores + scoresExceptPawn;
    }

    private double calculatePawnScores() {
        Map<File, Integer> pawnCount = new EnumMap<>(File.class);

        pieces.keySet().stream()
                .filter(this::hasPawnOn)
                .map(Position::getFile)
                .forEach(file -> pawnCount.put(file, pawnCount.getOrDefault(file, 0) + 1));

        return pawnCount.values().stream()
                .mapToDouble(PieceType::sumPawnScores)
                .sum();
    }

    private double calculateScoresExceptPawn() {
        return pieces.values().stream()
                .filter(PieceType::isNotPawn)
                .mapToDouble(PieceType::findScoreBy)
                .sum();
    }
}
