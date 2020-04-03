package mychess.domain.piece;

import mychess.domain.board.BoardDto;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

import java.util.Observer;

public class Queen extends Piece {

    public Queen(Position position, Player owner, Observer observer) {
        super(PieceType.QUEEN, position, owner, observer);
    }

    @Override
    protected void moveImpl(Position destination, BoardDto boardDto) {
        this.position = destination;
    }
}