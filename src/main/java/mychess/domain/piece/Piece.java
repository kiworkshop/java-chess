package mychess.domain.piece;

import mychess.domain.board.BoardDto;
import mychess.domain.player.Player;
import mychess.domain.position.Position;

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

    public char getSymbol() {
        switch (owner) {
            case BLACK:
                return type.getBlackSymbol();
            case WHITE:
                return type.getWhiteSymbol();
            case NONE:
                return type.getWhiteSymbol();
            default: return type.getWhiteSymbol();
        }
    }

    public PieceDto toDto() {
        return PieceDto.of(type, position, owner);
    }

    public void move(Position destination, BoardDto currentBoard) {
        moveImpl(destination, currentBoard);
        notifyObservers();
    }

    protected abstract void moveImpl(Position destination, BoardDto boardDto);
}
