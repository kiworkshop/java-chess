package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;

public class Queen extends Piece {
    private final String symbol = "q";

    private Queen(Team team, Position position) {
        super(team, position);
    }

    public static Queen of(Team team, Position position) {
        return new Queen(team, position);
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
