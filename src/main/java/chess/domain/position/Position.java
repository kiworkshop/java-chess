package chess.domain.position;

public class Position {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x.equals(position.x) && y.equals(position.y);
    }
}
