package mychess.domain.piece;

import mychess.domain.player.Player;
import mychess.domain.position.Position;

public class PieceDto {
    private PieceType type;
    private Position position;
    private Player owner;

    public PieceDto(PieceType type, Position position, Player owner) {
        this.type = type;
        this.position = position;
        this.owner = owner;
    }

    public static PieceDto of(PieceType type, Position position, Player owner) {
        return new PieceDto(type, position, owner);
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public Player getOwner() {
        return owner;
    }
}