package mychess.domain.position;

import mychess.domain.Board;

public class Position implements Comparable<Position> {

    private ICoordinate x;
    private ICoordinate y;

    private Position(ICoordinate x, ICoordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(ICoordinate x, ICoordinate y) {
        return new Position(x, y);
    }

    public static Position of(int x, int y) {
        return new Position(Coordinate.of(x), Coordinate.of(y));
    }

    public boolean isValid() {
        return x.isValid() && y.isValid();
    }

    public boolean isEndOfBoard() {
        return x.getValue() == Board.BOARD_SIZE - 1;
    }

    public int getX() {
        return x.getValue();
    }

    public int getY() {
        return y.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x.equals(position.x) && y.equals(position.y);
    }

    @Override
    public int compareTo(Position o) {
        int thisWeight = this.getX() + this.getY() * 10;
        int thatWeight = o.getX() + o.getY() * 10;

        return  thisWeight - thatWeight;
    }
}
