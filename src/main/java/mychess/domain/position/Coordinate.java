package mychess.domain.position;

public class Coordinate implements ICoordinate {

    public static final int BOARD_SIZE = 8;

    private int value;

    private Coordinate(int value) {
        this.value = value;
    }

    public static Coordinate of(int value) {
        return new Coordinate(value);
    }

    @Override
    public boolean isValid() {
        return 0 <= value && value < BOARD_SIZE;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return value == that.value;
    }
}
