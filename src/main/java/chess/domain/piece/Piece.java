package chess.domain.piece;

import lombok.Getter;

@Getter
public abstract class Piece {
    protected String name;
    protected Team team;
    protected PiecePosition piecePosition;
    protected String displayName;

    public Piece(String name, Team team, PiecePosition piecePosition) {
        this.name = name;
        this.team = team;
        this.displayName = team.displayName(name);
        this.piecePosition = piecePosition;
    }

    public abstract boolean movable(PiecePosition targetPosition);

    public abstract Piece move(PiecePosition tarPiecePosition);

}
