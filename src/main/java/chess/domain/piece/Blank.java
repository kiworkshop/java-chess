package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {
    private static final String BLANK_SYMBOL = ".";
    private static final float BLANK_SCORE = 0;

    protected Blank(Position position) {
        super(Team.NEUTRAL, position);
    }

    public static Blank of(Position position) {
        return new Blank(position);
    }

    @Override
    public boolean canMove(Piece source, Piece target) {
        notBlankPosition(source);
        withoutSameTeam(source.team(), target);
        return false;
    }

    @Override
    public List<Position> getMovablePositions() {
        return new ArrayList<>();
    }

    @Override
    public double score() {
        return BLANK_SCORE;
    }

    @Override
    public String symbol() {
        return BLANK_SYMBOL;
    }
}
