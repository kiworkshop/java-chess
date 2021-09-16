package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.BlankMoveStrategy;
import chess.domain.position.Position;

public class Blank extends Piece {
    private static final String BLANK_SYMBOL = ".";
    private static final float BLANK_SCORE = 0;

    protected Blank(Position position) {
        super(Team.NEUTRAL, position, new BlankMoveStrategy());
    }

    public static Blank of(Position position) {
        return new Blank(position);
    }

    @Override
    public String symbol() {
        return BLANK_SYMBOL;
    }

    @Override
    public double score() {
        return BLANK_SCORE;
    }
}
