package mychess.domain.piece;

import mychess.domain.board.BoardDto;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

import java.util.Observer;

public class Knight extends Piece {

    public Knight(Position position, Player owner, Observer observer) {
        super(PieceType.KNIGHT, position, owner, observer);
    }

    @Override
    protected void moveImpl(Position destination, BoardDto boardDto) {
        this.position = destination;
    }
}
