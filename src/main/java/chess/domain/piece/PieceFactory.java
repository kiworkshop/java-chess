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
            return initializePieces(Rank.R1, Rank.R2, WHITE);
        }

        return initializePieces(Rank.R8, Rank.R7, BLACK);
    }

    private static Map<Position, Piece> initializePieces(final Rank rank, final Rank pawnRank, final Color color) {
        Map<Position, Piece> pieces = new HashMap<>();
        initializePiecesExceptForPawns(rank, color, pieces);
        initializePawns(pawnRank, color, pieces);
        return pieces;
    }

    private static void initializePiecesExceptForPawns(final Rank rank, final Color color, final Map<Position, Piece> pieces) {
        pieces.put(Position.from(File.A, rank), new Rook(color));
        pieces.put(Position.from(File.B, rank), new Knight(color));
        pieces.put(Position.from(File.C, rank), new Bishop(color));
        pieces.put(Position.from(File.D, rank), new Queen(color));
        pieces.put(Position.from(File.E, rank), new King(color));
        pieces.put(Position.from(File.F, rank), new Bishop(color));
        pieces.put(Position.from(File.G, rank), new Knight(color));
        pieces.put(Position.from(File.H, rank), new Rook(color));
    }

    private static void initializePawns(final Rank pawnRank, final Color color, final Map<Position, Piece> pieces) {
        Arrays.stream(File.values())
                .forEach(file -> pieces.put(Position.from(file, pawnRank), new Pawn(color)));
    }
}
