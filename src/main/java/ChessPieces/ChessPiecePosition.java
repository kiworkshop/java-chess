package ChessPieces;


public class ChessPiecePosition {
    private int x;
    private int y;

    private ChessPiecePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static ChessPiecePosition getPositionByArray(int x, int y) {
        return new ChessPiecePosition(x, y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

}
