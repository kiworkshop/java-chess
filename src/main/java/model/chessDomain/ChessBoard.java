package model.chessDomain;

import model.chessDomain.pieces.*;

public class ChessBoard {
    public static final int GRID_SIZE = 8;

    private ChessPiece grid[][] = {
            {
                    new Rook(PieceColor.WHITE),
                    new Knight(PieceColor.WHITE),
                    new Bishop(PieceColor.WHITE),
                    new Queen(PieceColor.WHITE),
                    new King(PieceColor.WHITE),
                    new Bishop(PieceColor.WHITE),
                    new Knight(PieceColor.WHITE),
                    new Rook(PieceColor.WHITE)
            },
            {
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
                    new Pawn(PieceColor.WHITE),
            },
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
                    new Pawn(PieceColor.BLACK),
            },
            {
                    new Rook(PieceColor.BLACK),
                    new Knight(PieceColor.BLACK),
                    new Bishop(PieceColor.BLACK),
                    new Queen(PieceColor.BLACK),
                    new King(PieceColor.BLACK),
                    new Bishop(PieceColor.BLACK),
                    new Knight(PieceColor.BLACK),
                    new Rook(PieceColor.BLACK)
            }
    };

    public void move(Position source, Position destination) {
        grid[destination.getY()][destination.getX()] = grid[source.getY()][source.getX()];
        grid[source.getY()][source.getX()] = null;
    }

    public ChessPiece get(Position pos) {
        return grid[pos.getY()][pos.getX()];
    }

    public ChessBoardSnapshot getBoardSnapshot() {
        return ChessBoardSnapshot.of(grid);
    }
}
