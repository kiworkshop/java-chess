package chess.domain.piece;

import chess.domain.team.Team;
import lombok.Getter;

@Getter
public abstract class Piece {
    protected String name;
    protected Team team;
    protected PiecePosition piecePosition;
    protected String displayName;
    protected double score;

    public Piece(String name, Team team, PiecePosition piecePosition, double score) {
        this.name = name;
        this.team = team;
        this.displayName = team.displayName(name);
        this.piecePosition = piecePosition;
        this.score = score;
    }

    public abstract boolean movable(PiecePosition targetPosition);

    public void move(PiecePosition targetPosition){
        piecePosition = targetPosition;
    }

}
