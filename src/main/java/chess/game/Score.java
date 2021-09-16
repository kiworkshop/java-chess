package chess.game;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.piece.Pawn.PAWN_SAME_FILE_SCORE;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Score {
    private static final int PAWN_SAME_FILE_COUNT = 2;
    private final double white;
    private final double black;

    private Score(double white, double black) {
        this.white = white;
        this.black = black;
    }

    public static Score of(double white, double black) {
        return new Score(white, black);
    }

    public static double calculateSum(List<Piece> pieces) {
        long kingCount = existKing(pieces);
        double sum = pieces.stream()
                .mapToDouble(Piece::score)
                .sum();
        int sameFileCount = isSameFile(pieces);
        return (sum - (sameFileCount * PAWN_SAME_FILE_SCORE)) * kingCount;
    }

    private static long existKing(List<Piece> pieces) {
        return pieces.stream()
                .filter(King.class::isInstance)
                .count();
    }

    private static int isSameFile(List<Piece> pieces) {
        Map<Integer, Long> counting = pieces.stream()
                .filter(Score::isPawn)
                .collect(Collectors.toList())
                .stream()
                .collect(groupingBy(Piece::getFileNumber, counting()));

        return (int) counting.values().stream()
                .filter(count -> count >= PAWN_SAME_FILE_COUNT)
                .mapToLong(Long::valueOf)
                .sum();
    }

    private static boolean isPawn(Piece piece) {
        return piece instanceof Pawn;
    }

    public double white() {
        return white;
    }

    public double black() {
        return black;
    }
}
