package chess.domain.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum PieceResolver {

    KING(King.class, "k", 0),
    QUEEN(Queen.class, "q", 9),
    ROOK(Rook.class, "r", 5),
    BISHOP(Bishop.class, "b", 3),
    KNIGHT(Knight.class, "n", 2.5),
    PAWN(Pawn.class, "p", 1);

    private static final Map<Class<? extends Piece>, PieceResolver> PIECE_RESOLVERS = createPieceResolvers();
    private static final int DUPLICATION_THRESHOLD = 1;
    private static final double PAWN_SCORE_ON_DUPLICATION = 0.5;

    private final Class<? extends Piece> piece;
    private final String name;
    private final double score;

    PieceResolver(final Class<? extends Piece> piece, final String name, final double score) {
        this.piece = piece;
        this.name = name;
        this.score = score;
    }

    private static Map<Class<? extends Piece>, PieceResolver> createPieceResolvers() {
        Map<Class<? extends Piece>, PieceResolver> pieceResolvers = new HashMap<>();

        Arrays.stream(values())
                .forEach(pieceResolver -> pieceResolvers.put(pieceResolver.piece, pieceResolver));

        return Collections.unmodifiableMap(pieceResolvers);
    }

    public static String findNameBy(final Piece piece) {
        PieceResolver pieceResolver = PieceResolver.of(piece);

        if (piece.isWhite()) {
            return pieceResolver.name;
        }

        return pieceResolver.name.toUpperCase();
    }

    public static PieceResolver of(final Piece piece) {
        PieceResolver pieceResolver = PIECE_RESOLVERS.get(piece.getClass());

        if (pieceResolver == null) {
            throw new IllegalArgumentException("기물에 대한 일치하는 리졸버가 존재하지 않습니다.");
        }

        return pieceResolver;
    }

    public static double findScoreBy(final Piece piece) {
        PieceResolver pieceResolver = PieceResolver.of(piece);
        return pieceResolver.score;
    }

    public static double sumPawnScores(final int pawnCount) {
        if (pawnCount > DUPLICATION_THRESHOLD) {
            return pawnCount * PAWN_SCORE_ON_DUPLICATION;
        }

        return PAWN.score;
    }
}
