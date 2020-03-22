package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public interface Movable {
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot);
}
