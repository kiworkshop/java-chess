package chess.domain.player;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),

    NORTH_EAST_NORTH(1, 2),
    NORTH_EAST_EAST(2, 1),
    NORTH_WEST_NORTH(-1, 2),
    NORTH_WEST_WEST(-2, 1),
    SOUTH_EAST_SOUTH(1, -2),
    SOUTH_EAST_EAST(2, -1),
    SOUTH_WEST_SOUTH(-1, -2),
    SOUTH_WEST_WEST(-2, -1);

    private static final int DIVISIBLE_STANDARD = 0;
    private static final int CARDINAL_STANDARD = 0;
    private static final int SIGN_STANDARD = 0;

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public boolean matches(final int x, final int y) {
        if (isNorthOrSouth()) {
            return this.x == x && isYDivisible(y) && hasSameSign(x, y);
        }

        if (isEastOrWest()) {
            return this.y == y && isXDivisible(x) && hasSameSign(x, y);
        }

        return isMultiple(x, y) && hasSameSign(x, y);
    }

    private boolean isNorthOrSouth() {
        return this.x == CARDINAL_STANDARD;
    }

    private boolean isEastOrWest() {
        return this.y == CARDINAL_STANDARD;
    }

    private boolean isYDivisible(final int y) {
        return (y % this.y) == DIVISIBLE_STANDARD;
    }

    private boolean isXDivisible(final int x) {
        return (x % this.x) == DIVISIBLE_STANDARD;
    }

    private boolean isMultiple(final int x, final int y) {
        return hasSameRatio(x, y) && isDivisible(x, y);
    }

    private boolean hasSameRatio(final int x, final int y) {
        return (x / this.x) == (y / this.y);
    }

    private boolean isDivisible(final int x, final int y) {
        return isXDivisible(x) && isYDivisible(y);
    }

    private boolean hasSameSign(final int x, final int y) {
        return (this.x ^ x) >= SIGN_STANDARD && (this.y ^ y) >= SIGN_STANDARD;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
