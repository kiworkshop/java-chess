package chess.domain.piece.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum PieceType {

    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1);

    private static final Map<Class<? extends Piece>, PieceType> PIECE_TYPE_MAP = createPieceTypeMap();
    private static final int DUPLICATION_THRESHOLD = 1;
    private static final double PAWN_SCORE_ON_DUPLICATION = 0.5;

    private final String name;
    private final double score;

    PieceType(final String name, final double score) {
        this.name = name;
        this.score = score;
    }

    private static Map<Class<? extends Piece>, PieceType> createPieceTypeMap() {
        Map<Class<? extends Piece>, PieceType> map = new HashMap<>();
        map.put(King.class, KING);
        map.put(Queen.class, QUEEN);
        map.put(Rook.class, ROOK);
        map.put(Bishop.class, BISHOP);
        map.put(Knight.class, KNIGHT);
        map.put(Pawn.class, PAWN);
        return Collections.unmodifiableMap(map);
    }

    public static PieceType of(final Piece piece) {
        return PIECE_TYPE_MAP.get(piece.getClass());
    }

    public static String findNameBy(final Piece piece) {
        PieceType pieceType = PieceType.of(piece);

        if (piece.isWhite()) {
            return pieceType.name.toLowerCase();
        }
        return pieceType.name.toUpperCase();
    }

    public static double findScoreBy(final Piece piece) {
        PieceType pieceType = PieceType.of(piece);
        return pieceType.score;
    }

    public static boolean isKing(final Piece piece) {
        return PieceType.of(piece) == KING;
    }

    public static boolean isPawn(final Piece piece) {
        return PieceType.of(piece) == PAWN;
    }

    public static boolean isNotPawn(final Piece piece) {
        return !isPawn(piece);
    }

    public static double sumPawnScores(final int count) {
        if (count > DUPLICATION_THRESHOLD) {
            return count * PAWN_SCORE_ON_DUPLICATION;
        }
        return PAWN.score;
    }
}
