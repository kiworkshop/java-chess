package model.chessDomain;

import model.chessDomain.ChessBoard;
import model.chessDomain.Position;
import model.chessDomain.pieces.ChessPiece;

public class ChessBoardSnapshot {
    private ChessPiece grid[][];

    private ChessBoardSnapshot(ChessPiece[][] grid) {
        this.grid = new ChessPiece[ChessBoard.GRID_SIZE][ChessBoard.GRID_SIZE];
        for (int i = 0; i < ChessBoard.GRID_SIZE; i++) {
            for (int j = 0; j < ChessBoard.GRID_SIZE; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public static ChessBoardSnapshot of(ChessPiece[][] grid) {
        return new ChessBoardSnapshot(grid);
    }

    public ChessPiece get(Position pos) {
        return grid[pos.getY()][pos.getX()];
    }
}
