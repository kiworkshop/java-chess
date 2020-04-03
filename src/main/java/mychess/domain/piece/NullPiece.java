package mychess.domain.piece;

import mychess.domain.board.BoardDto;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

import java.util.Observer;

public class NullPiece extends Piece {

    public NullPiece(Position position, Player owner, Observer observer) {
        super(PieceType.NULL, position, owner, observer);
    }

    @Override
    protected void moveImpl(Position destination, BoardDto boardDto) {
        this.position = destination;
    }
}
