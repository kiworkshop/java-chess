package model.chessDomain;

public class ChessBoard {
    public static final int GRID_SIZE = 8;

    private ChessPiece grid[][] = {
            {
                    new ChessPiece(PieceColor.WHITE, PieceType.ROOK),
                    new ChessPiece(PieceColor.WHITE, PieceType.KNIGHT),
                    new ChessPiece(PieceColor.WHITE, PieceType.BISHOP),
                    new ChessPiece(PieceColor.WHITE, PieceType.QUEEN),
                    new ChessPiece(PieceColor.WHITE, PieceType.KING),
                    new ChessPiece(PieceColor.WHITE, PieceType.BISHOP),
                    new ChessPiece(PieceColor.WHITE, PieceType.KNIGHT),
                    new ChessPiece(PieceColor.WHITE, PieceType.ROOK)
            },
            {
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN),
                    new ChessPiece(PieceColor.WHITE, PieceType.PAWN)
            },
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN),
                    new ChessPiece(PieceColor.BLACK, PieceType.PAWN)
            },
            {
                    new ChessPiece(PieceColor.BLACK, PieceType.ROOK),
                    new ChessPiece(PieceColor.BLACK, PieceType.KNIGHT),
                    new ChessPiece(PieceColor.BLACK, PieceType.BISHOP),
                    new ChessPiece(PieceColor.BLACK, PieceType.QUEEN),
                    new ChessPiece(PieceColor.BLACK, PieceType.KING),
                    new ChessPiece(PieceColor.BLACK, PieceType.BISHOP),
                    new ChessPiece(PieceColor.BLACK, PieceType.KNIGHT),
                    new ChessPiece(PieceColor.BLACK, PieceType.ROOK)
            }
    };

    public void move(int sourceY, int sourceX, int destinationY, int destinationX) {
        grid[destinationY][destinationX] = grid[sourceY][sourceX];
        grid[sourceY][sourceX] = null;
    }

    public ChessPiece get(int y, int x) {
        return grid[y][x];
    }
}
