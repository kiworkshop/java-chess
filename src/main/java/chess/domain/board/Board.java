package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.move.Path;
import chess.domain.piece.type.Piece;
import chess.domain.player.Color;
import chess.domain.player.Player;
import chess.domain.player.Scores;
import chess.exception.EmptyPositionException;

import static chess.domain.player.Color.BLACK;
import static chess.domain.player.Color.WHITE;

public class Board {

    private final Player whitePlayer;
    private final Player blackPlayer;
    private Color currentTurn;

    public Board() {
        this.whitePlayer = new Player(WHITE);
        this.blackPlayer = new Player(BLACK);
        this.currentTurn = WHITE;
    }

    public void move(final MoveParameters moveParameters) {
        Position source = moveParameters.getSource();
        Position target = moveParameters.getTarget();
        validateParameters(source, target);

        movePiece(source, target);
        currentTurn = currentTurn.flip();
    }

    private void validateParameters(final Position source, final Position target) {
        validateSourceOwner(source);
        validateSamePosition(source, target);
        validateTargetOwner(target);
        validateKingMovable(source, target);
    }

    private void validateSourceOwner(final Position source) {
        if (currentPlayer().hasNoPieceOn(source)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void validateSamePosition(final Position source, final Position target) {
        if (source.isSame(target)) {
            throw new IllegalArgumentException("출발 위치와 도착 위치가 같을 수 없습니다.");
        }
    }

    private void validateTargetOwner(final Position target) {
        if (currentPlayer().hasPieceOn(target)) {
            throw new IllegalArgumentException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateKingMovable(final Position source, final Position target) {
        if (currentPlayer().hasKingOn(source) && canEnemyAttack(target)) {
            throw new IllegalArgumentException("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canEnemyAttack(final Position target) {
        return enemyPlayer().findAttackPaths(target).stream()
                .anyMatch(path -> path.isNotBlockedBy(whitePlayer) && path.isNotBlockedBy(blackPlayer));
    }

    private void movePiece(final Position source, final Position target) {
        if (currentPlayer().isPawnAttacking(source, target) && enemyPlayer().hasNoPieceOn(target)) {
            throw new IllegalArgumentException("폰은 공격 대상이 있는 경우에만 대각선으로 이동할 수 있습니다.");
        }

        Path path = currentPlayer().findMovePath(source, target);
        validatePathNotBlocked(path);

        enemyPlayer().wasAttackedBy(target);
        currentPlayer().move(source, target);
    }

    private void validatePathNotBlocked(final Path path) {
        if (path.isBlockedBy(whitePlayer) || path.isBlockedBy(blackPlayer)) {
            throw new IllegalArgumentException("다른 기물을 통과하여 이동할 수 없습니다.");
        }
    }

    public Piece findBy(final Position position) {
        if (isEmpty(position)) {
            throw new EmptyPositionException();
        }

        if (whitePlayer.hasPieceOn(position)) {
            return whitePlayer.findPieceBy(position);
        }
        return blackPlayer.findPieceBy(position);
    }

    private boolean isEmpty(Position position) {
        return whitePlayer.hasNoPieceOn(position) && blackPlayer.hasNoPieceOn(position);
    }

    public Scores getScores() {
        double whiteScore = whitePlayer.calculateScores();
        double blackScore = blackPlayer.calculateScores();

        return new Scores(whiteScore, blackScore);
    }

    public Color getWinner() {
        if (isBothKingAlive()) {
            throw new IllegalStateException("King이 잡히지 않아 승자가 없습니다.");
        }

        if (whitePlayer.isKingDead()) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isBothKingAlive() {
        return whitePlayer.isKingAlive() && blackPlayer.isKingAlive();
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    private Player currentPlayer() {
        if (currentTurn.isWhite()) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    private Player enemyPlayer() {
        if (currentTurn.isWhite()) {
            return blackPlayer;
        }
        return whitePlayer;
    }
}
