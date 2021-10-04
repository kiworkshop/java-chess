package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.KingMoveStrategy;
import chess.domain.position.Position;

public class King extends Piece {
    private static final String KING_SYMBOL = "k";
    private static final int KING_SCORE = 0;

    private King(Team team, Position position) {
        super(team, position, new KingMoveStrategy());
    }

    public static King of(Team team, Position position) {
        return new King(team, position);
    }

    @Override
    public String symbol() {
        return KING_SYMBOL;
    }

    @Override
    public double score() {
        return KING_SCORE;
    }
}
