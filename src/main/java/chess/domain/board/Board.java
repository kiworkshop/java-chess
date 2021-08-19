package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> board = new HashMap<>();

    public Board() {
        initializePawns();
        initializeOthers(Rank.R1, Color.WHITE);
        initializeOthers(Rank.R8, Color.BLACK);
    }

    private void initializeOthers(Rank rank, Color color) {
        board.put(Position.from(File.a, rank), new Rook(color));
        board.put(Position.from(File.b, rank), new Knight(color));
        board.put(Position.from(File.c, rank), new Bishop(color));
        board.put(Position.from(File.d, rank), new Queen(color));
        board.put(Position.from(File.e, rank), new King(color));
        board.put(Position.from(File.f, rank), new Bishop(color));
        board.put(Position.from(File.g, rank), new Knight(color));
        board.put(Position.from(File.h, rank), new Rook(color));
    }

    private void initializePawns() {
        Arrays.stream(File.values())
                .forEach(file -> {
                    board.put(Position.from(file, Rank.R2), new Pawn(Color.WHITE));
                    board.put(Position.from(file, Rank.R7), new Pawn(Color.BLACK));
                });
    }

    public void move(final MoveParameters moveParameters, final boolean isWhiteTurn) {
        Position source = moveParameters.getSource();
        Position target = moveParameters.getTarget();
        Piece sourcePiece = findBy(source);

        validateOwner(isWhiteTurn, sourcePiece.isWhite());
        validateSamePosition(source, target);
        validateTarget(target, sourcePiece);

        Set<Position> paths = sourcePiece.findPaths(source, target);
        validatePathsEmpty(paths);

        board.put(target, sourcePiece);
        board.remove(source);
    }

    private void validateTarget(final Position target, final Piece sourcePiece) {
        if (isEmpty(target)) {
            return;
        }

        Piece targetPiece = board.get(target);
        if (sourcePiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException("같은 색상의 기물은 공격할 수 없습니다.");
        }
    }

    public Piece findBy(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return board.get(position);
    }

    public boolean isEmpty(Position position) {
        return !board.containsKey(position);
    }

    private void validateOwner(final boolean expectedColor, final boolean sourcePieceColor) {
        if (sourcePieceColor != expectedColor) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void validateSamePosition(final Position source, final Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발 위치와 도착 위치가 같을 수 없습니다.");
        }
    }

    private void validatePathsEmpty(final Set<Position> paths) {
        boolean isPresent = paths.stream()
                .anyMatch(board::containsKey);

        if (isPresent) {
            throw new IllegalArgumentException("기물을 통과하여 이동할 수 없습니다.");
        }
    }
}
