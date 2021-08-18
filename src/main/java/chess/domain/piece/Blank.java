package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Blank extends Piece {
    private final String symbol = ".";

    protected Blank(Position position) {
        super(Team.NEUTRAL, position);
    }

    public static Blank of(Position position) {
        return new Blank(position);
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
