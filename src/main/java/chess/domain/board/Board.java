package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Position;

import java.util.Set;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class Board {
    private final Player white;
    private final Player black;

    public Board() {
        this.white = new Player(Color.WHITE);
        this.black = new Player(BLACK);
    }

    public void move(final MoveParameters moveParameters, final Color currentTurn) {
        Player player = currentPlayer(currentTurn);
        Player enemy = enemyPlayer(currentTurn);
        Position source = moveParameters.getSource();
        Position target = moveParameters.getTarget();

        validateSourceOwner(enemy, source);
        validateSamePosition(source, target);
        validateTarget(player, target);
        validateKingMovable(player, enemy, source, target);

        enemy.attacked(target);
        movePiece(player, source, target);
    }

    private Player currentPlayer(final Color currentTurn) {
        if (currentTurn == Color.WHITE) {
            return white;
        }
        return black;
    }

    private Player enemyPlayer(final Color currentTurn) {
        if (currentTurn == Color.WHITE) {
            return black;
        }
        return white;
    }

    private void validateSourceOwner(final Player enemy, final Position source) {
        if (enemy.hasPieceOn(source)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void validateSamePosition(final Position source, final Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발 위치와 도착 위치가 같을 수 없습니다.");
        }
    }

    private void validateTarget(final Player player, final Position target) {
        if (player.hasPieceOn(target)) {
            throw new IllegalArgumentException("같은 색상의 기물은 공격할 수 없습니다.");
        }
    }

    private void validateKingMovable(final Player player, final Player enemy, final Position source, final Position target) {
        if (player.hasKingOn(source) && enemy.canAttack(target)) {
            throw new IllegalArgumentException("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Player player, final Position source, final Position target) {
        Set<Position> paths = player.findPaths(source, target);
        validatePathsEmpty(paths);

        player.update(source, target);
    }

    private void validatePathsEmpty(final Set<Position> paths) {
        boolean isWhiteBlocked = paths.stream()
                .anyMatch(white::hasPieceOn);
        boolean isBlackBlocked = paths.stream()
                .anyMatch(black::hasPieceOn);

        if (isWhiteBlocked || isBlackBlocked) {
            throw new IllegalArgumentException("기물을 통과하여 이동할 수 없습니다.");
        }
    }

    public Piece findBy(final Position position) {
        if (white.hasPieceOn(position)) {
            return white.findPieceBy(position);
        }

        return black.findPieceBy(position);
    }

    public boolean isEmpty(final Position position) {
        return !white.hasPieceOn(position) && !black.hasPieceOn(position);
    }

    public Scores getScores() {
        double whiteScore = white.calculateScores();
        double blackScore = black.calculateScores();

        return new Scores(whiteScore, blackScore);
    }

    public boolean isAnyKingDead() {
        return white.isKingDead() || black.isKingDead();
    }

    public boolean isBothKingAlive() {
        return white.isKingAlive() && black.isKingAlive();
    }

    public Color getWinner() {
        if (white.isKingDead()) {
            return BLACK;
        }
        return WHITE;
    }
}
