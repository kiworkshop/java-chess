package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.movement.RookMoveStrategy;
import chess.domain.position.Position;

public class Rook extends Piece {
    private static final String ROOK_SYMBOL = "r";
    private static final int ROOK_SCORE = 5;

    public Rook(Team team, Position position) {
        super(team, position, new RookMoveStrategy());
    }

    public static Rook of(Team team, Position position) {
        return new Rook(team, position);
    }

    @Override
    public String symbol() {
        return ROOK_SYMBOL;
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
