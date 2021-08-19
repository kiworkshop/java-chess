package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Rook extends Piece {
    private final String symbol = "r";

    private Rook(Team team, Position position) {
        super(team, position);
    }

    public static Rook of(Team team, Position position) {
        return new Rook(team, position);
    }

    @Override
    public String symbol() {
        if (team.equals(Team.BLACK)) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
