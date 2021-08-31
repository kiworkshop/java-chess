package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.player.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Map<Position, Piece> createPieces(final Color color) {
        if (color.isWhite()) {
            return whitePieces();
        }
        return blackPieces();
    }

    private static Map<Position, Piece> whitePieces() {
        return initializePieces(Rank.R1, Rank.R2, WHITE);
    }

    private static Map<Position, Piece> blackPieces() {
        return initializePieces(Rank.R8, Rank.R7, BLACK);
    }

    private static Map<Position, Piece> initializePieces(final Rank rank, final Rank pawnRank, final Color color) {
        Map<Position, Piece> board = new HashMap<>();
        initializePiecesExceptForPawns(rank, color, board);
        initializePawns(pawnRank, color, board);
        return board;
    }

    private static void initializePiecesExceptForPawns(final Rank rank, final Color color, final Map<Position, Piece> board) {
        board.put(Position.from(File.a, rank), new Rook(color));
        board.put(Position.from(File.b, rank), new Knight(color));
        board.put(Position.from(File.c, rank), new Bishop(color));
        board.put(Position.from(File.d, rank), new Queen(color));
        board.put(Position.from(File.e, rank), new King(color));
        board.put(Position.from(File.f, rank), new Bishop(color));
        board.put(Position.from(File.g, rank), new Knight(color));
        board.put(Position.from(File.h, rank), new Rook(color));
    }

    private static void initializePawns(final Rank pawnRank, final Color color, final Map<Position, Piece> board) {
        Arrays.stream(File.values())
                .forEach(file -> board.put(Position.from(file, pawnRank), new Pawn(color)));
    }
}
