package chess.domain.piece;

import chess.domain.Player;
import chess.domain.board.ChessBoardDto;
import chess.domain.position.Position;

import java.util.Observer;

public class Pawn extends Piece {
    private Pawn(Position position, Player owner, Observer observer) {
        super(PieceType.PAWN, position, owner, observer);
    }

    public static Pawn of(Position position, Player owner, Observer observer) {
        return new Pawn(position, owner, observer);
    }

    @Override
    protected void moveImpl(Position destination, ChessBoardDto chessBoardDto) {
        this.position = destination;
    }
}
