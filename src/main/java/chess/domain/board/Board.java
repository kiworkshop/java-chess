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

        validateColor(isWhiteTurn, sourcePiece.isWhite());

        Piece targetPiece = findBy(target);
        if (sourcePiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException("같은 색상의 기물은 공격할 수 없습니다.");
        }

        board.put(target, sourcePiece);
        board.remove(source);
    }

    private void validateColor(final boolean expectedColor, final boolean sourcePieceColor) {
        if (sourcePieceColor != expectedColor) {
            throw new IllegalArgumentException("같은 색상의 기물만 움직일 수 있습니다.");
        }
    }

    public Piece findBy(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        return board.get(position);
    }

    public boolean isEmpty(Position position) {
        return board.get(position) == null;
    }
}
