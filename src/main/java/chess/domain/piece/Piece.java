package chess.domain.piece;

import lombok.Getter;

@Getter
public abstract class Piece {
    protected String name;
    protected Team team;
    protected PiecePosition piecePosition;
    protected String displayName;

    protected Piece(String name, Team team) {
        this.name = name;
        this.team = team;
        this.displayName = team.displayName(name);
    }
}
