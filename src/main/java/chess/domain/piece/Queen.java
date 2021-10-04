package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.QueenMoveStrategy;
import chess.domain.position.Position;

public class Queen extends Piece {
    private static final String QUEEN_SYMBOL = "q";
    private static final int QUEEN_SCORE = 9;

    private Queen(Team team, Position position) {
        super(team, position, new QueenMoveStrategy());
    }

    public static Queen of(Team team, Position position) {
        return new Queen(team, position);
    }

    @Override
    public String symbol() {
        return QUEEN_SYMBOL;
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }
}
