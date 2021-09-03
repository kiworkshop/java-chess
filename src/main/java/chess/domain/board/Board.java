package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Scores;

import java.util.Set;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

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
        currentTurn = currentTurn.nextTurn();
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
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발 위치와 도착 위치가 같을 수 없습니다.");
        }
    }

    private void validateTargetOwner(final Position target) {
        if (currentPlayer().hasPieceOn(target)) {
            throw new IllegalArgumentException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateKingMovable(final Position source, final Position target) {
        if (currentPlayer().hasKingOn(source) && enemyPlayer().canAttack(target)) {
            throw new IllegalArgumentException("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
        }
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

    private void movePiece(final Position source, final Position target) {
        Set<Position> paths = currentPlayer().findPaths(source, target);
        validatePathsEmpty(paths);

        enemyPlayer().isUnderAttack(target);
        currentPlayer().move(source, target);
    }

    private void validatePathsEmpty(final Set<Position> paths) {
        boolean isWhiteBlocked = paths.stream()
                .anyMatch(whitePlayer::hasPieceOn);
        boolean isBlackBlocked = paths.stream()
                .anyMatch(blackPlayer::hasPieceOn);

        if (isWhiteBlocked || isBlackBlocked) {
            throw new IllegalArgumentException("다른 기물을 통과하여 이동할 수 없습니다.");
        }
    }

    public boolean isEmpty(final Position position) {
        return whitePlayer.hasNoPieceOn(position) && blackPlayer.hasNoPieceOn(position);
    }

    public Piece findBy(final Position position) {
        if (whitePlayer.hasPieceOn(position)) {
            return whitePlayer.findPieceBy(position);
        }
        return blackPlayer.findPieceBy(position);
    }

    public Color getCurrentTurn() {
        return currentTurn;
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
}
