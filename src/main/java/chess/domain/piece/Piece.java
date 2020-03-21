package chess.domain.piece;

import chess.domain.Player;
import chess.domain.board.ChessBoardDto;
import chess.domain.position.Position;

import java.util.Observable;
import java.util.Observer;


public abstract class Piece extends Observable {
    protected PieceType type;
    protected Position position;
    protected Player owner;

    public Piece(PieceType type, Position position, Player owner, Observer observer) {
        this.type = type;
        this.position = position;
        this.owner = owner;
        addObserver(observer);
    }

    public Position getPosition() {
        return position;
    }

    public PieceDto toDto() {
        return PieceDto.of(type, position, owner);
    }

    public void move(Position destination, ChessBoardDto currentBoard) {
        moveImpl(destination, currentBoard);
        notifyObservers();
    }

    protected abstract void moveImpl(Position destination, ChessBoardDto chessBoardDto);
}

