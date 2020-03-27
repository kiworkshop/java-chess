package chess.model.domain.game;

import chess.model.domain.game.board.ChessBoard;

public class Position {
    private int x, y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Position of(int x, int y) {
        return new Position(x, y);
    }
    public static Position of(String position) {
        return new Position(position.charAt(0) - 'a', 8 - (position.charAt(1) - '0'));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOutOfBound() {
        return x < 0 || x >= ChessBoard.GRID_SIZE || y < 0 || y >= ChessBoard.GRID_SIZE;
    }
}
