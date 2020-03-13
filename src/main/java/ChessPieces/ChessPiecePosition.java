package ChessPieces;


import java.util.Objects;

public class ChessPiecePosition {
    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiecePosition that = (ChessPiecePosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private ChessPiecePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static ChessPiecePosition getPositionByArray(int x, int y) {
        return new ChessPiecePosition(x, y);
    }

    public static ChessPiecePosition getPositionByString(String inputPosition) {
        String UppercaseInputPosition = inputPosition.toUpperCase();
        char a = 'A';
        int x = (int) inputPosition.charAt(0) - (int) a + 1;
        int y = Integer.parseInt(String.valueOf(inputPosition.charAt(1)));
        return getPositionByArray(x, y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

}
