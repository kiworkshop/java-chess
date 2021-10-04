package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.KnightMoveStrategy;
import chess.domain.position.Position;

public class Knight extends Piece {
    private static final String KNIGHT_SYMBOL = "n";
    private static final double KNIGHT_SCORE = 2.5;

    private Knight(Team team, Position position) {
        super(team, position, new KnightMoveStrategy());
    }

    public static Knight of(Team team, Position position) {
        return new Knight(team, position);
    }

    @Override
    public String symbol() {
        return KNIGHT_SYMBOL;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }
}