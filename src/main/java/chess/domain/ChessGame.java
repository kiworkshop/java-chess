package chess.domain;

import chess.domain.board.Position;
import chess.domain.command.MoveParameters;
import chess.domain.piece.move.Path;
import chess.domain.piece.type.Piece;
import chess.domain.team.Color;
import chess.domain.team.Scores;
import chess.domain.team.Team;
import chess.exception.EmptyPositionException;

import static chess.domain.team.Color.BLACK;
import static chess.domain.team.Color.WHITE;

public class ChessGame {

    private final Team whiteTeam;
    private final Team blackTeam;
    private Color currentTurn;

    public ChessGame() {
        this.whiteTeam = Team.white();
        this.blackTeam = Team.black();
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
        if (currentTeam().hasNoPieceOn(source)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void validateSamePosition(final Position source, final Position target) {
        if (source.isSame(target)) {
            throw new IllegalArgumentException("출발 위치와 도착 위치가 같을 수 없습니다.");
        }
    }

    private void validateTargetOwner(final Position target) {
        if (currentTeam().hasPieceOn(target)) {
            throw new IllegalArgumentException("자신의 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateKingMovable(final Position source, final Position target) {
        if (currentTeam().hasKingOn(source) && canEnemyAttack(target)) {
            throw new IllegalArgumentException("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canEnemyAttack(final Position target) {
        return enemyTeam().findAttackPaths(target).stream()
                .anyMatch(path -> path.isNotBlockedBy(whiteTeam) && path.isNotBlockedBy(blackTeam));
    }

    private void movePiece(final Position source, final Position target) {
        if (currentTeam().isPawnAttacking(source, target) && enemyTeam().hasNoPieceOn(target)) {
            throw new IllegalArgumentException("폰은 공격 대상이 있는 경우에만 대각선으로 이동할 수 있습니다.");
        }

        Path path = currentTeam().findMovePath(source, target);
        validatePathNotBlocked(path);

        enemyTeam().removePiece(target);
        currentTeam().movePiece(source, target);
    }

    private void validatePathNotBlocked(final Path path) {
        if (path.isBlockedBy(whiteTeam) || path.isBlockedBy(blackTeam)) {
            throw new IllegalArgumentException("다른 기물을 통과하여 이동할 수 없습니다.");
        }
    }

    public Piece findPieceBy(final Position position) {
        if (isEmpty(position)) {
            throw new EmptyPositionException();
        }

        if (whiteTeam.hasPieceOn(position)) {
            return whiteTeam.findPieceBy(position);
        }
        return blackTeam.findPieceBy(position);
    }

    private boolean isEmpty(Position position) {
        return whiteTeam.hasNoPieceOn(position) && blackTeam.hasNoPieceOn(position);
    }

    public Scores getScores() {
        double whiteScore = whiteTeam.calculateScores();
        double blackScore = blackTeam.calculateScores();

        return new Scores(whiteScore, blackScore);
    }

    public Color getWinner() {
        if (isBothKingAlive()) {
            throw new IllegalStateException("King이 잡히지 않아 승자가 없습니다.");
        }

        if (whiteTeam.isKingDead()) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isBothKingAlive() {
        return whiteTeam.isKingAlive() && blackTeam.isKingAlive();
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    private Team currentTeam() {
        if (currentTurn.isWhite()) {
            return whiteTeam;
        }
        return blackTeam;
    }

    private Team enemyTeam() {
        if (currentTurn.isWhite()) {
            return blackTeam;
        }
        return whiteTeam;
    }
}
