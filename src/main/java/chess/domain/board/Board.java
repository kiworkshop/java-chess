package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.player.Player;

import java.util.Set;

public class Board {
    private final Player white;
    private final Player black;

    public Board() {
        this.white = new Player(Color.WHITE);
        this.black = new Player(Color.BLACK);
    }

    public void move(final MoveParameters moveParameters, final boolean isWhiteTurn) {
        Player player = currentPlayer(isWhiteTurn);
        Player enemy = currentPlayer(!isWhiteTurn);
        Position source = moveParameters.getSource();
        Position target = moveParameters.getTarget();

        validateSourceOwner(enemy, source);
        validateSamePosition(source, target);
        validateTarget(player, target);

        movePiece(player, source, target);
    }

    private Player currentPlayer(final boolean isWhiteTurn) {
        if (isWhiteTurn) {
            return white;
        }
        return black;
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

    public boolean isEmpty(Position position) {
        return !white.hasPieceOn(position) && !black.hasPieceOn(position);
    }
}
