package chess.domain.movement;

import chess.domain.board.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    N(0, 1),
    S(0, -1),
    E(1, 0),
    W(-1, 0),
    NE(1, 1),
    NW(-1, 1),
    SW(-1, -1),
    SE(1, -1),
    NN(0, 2),
    SS(0, -2),
    NNE(1, 2),
    NNW(-1, 2),
    NEE(2, 1),
    NWW(-2, 1),
    SSE(1, -2),
    SSW(-1, -2),
    SEE(2, -1),
    SWW(-2, -1);

    private final int fileDirection;
    private final int rankDirection;

    Direction(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static final List<Direction> LINEAR_DIRECTIONS = Arrays.asList(N, S, E, W);
    public static final List<Direction> DIAGONAL_DIRECTIONS = Arrays.asList(NE, NW, SW, SE);
    public static final List<Direction> KING_DIRECTIONS = Arrays.asList(N, S, E, W, NE, NW, SW, SE);
    public static final List<Direction> QUEEN_DIRECTIONS = Arrays.asList(N, S, E, W, NE, NW, SW, SE);
    public static final List<Direction> KNIGHT_DIRECTIONS = Arrays.asList(NNE, NNW, NEE, NWW, SSE, SSW, SEE, SWW);
    public static final List<Direction> WHITE_PAWN_DIRECTION = Collections.singletonList(N);
    public static final List<Direction> WHITE_PAWN_FIRST_DIRECTIONS = Collections.singletonList(NN);
    public static final List<Direction> WHITE_PAWN_ATTACK_DIRECTIONS = Arrays.asList(NE, NW);
    public static final List<Direction> BLACK_PAWN_DIRECTION = Collections.singletonList(S);
    public static final List<Direction> BLACK_PAWN_FIRST_DIRECTION = Collections.singletonList(SS);
    public static final List<Direction> BLACK_PAWN_ATTACK_DIRECTION = Arrays.asList(SE, SW);

    public static List<Direction> pawnDirection(Team team) {
        return (team.equals(Team.WHITE)) ? WHITE_PAWN_DIRECTION : BLACK_PAWN_DIRECTION;
    }

    public static List<Direction> pawnFirstDirection(Team team) {
        return (team.equals(Team.WHITE)) ? WHITE_PAWN_FIRST_DIRECTIONS : BLACK_PAWN_FIRST_DIRECTION;
    }

    public static List<Direction> pawnAttackDirection(Team team) {
        return (team.equals(Team.WHITE)) ? WHITE_PAWN_ATTACK_DIRECTIONS : BLACK_PAWN_ATTACK_DIRECTION;
    }

    public int findFileNumber(int fileNumber, int repeatCount) {
        return (fileDirection * repeatCount) + fileNumber;
    }

    public int findRankNumber(int rankNumber, int repeatCount) {
        return (rankDirection * repeatCount) + rankNumber;
    }
}
