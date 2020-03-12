package model.chessDomain;

public class ChessGame {
    private ChessBoard chessBoard;
    private PieceColor turn;

    public void start() {
        chessBoard = new ChessBoard();
        turn = PieceColor.BLACK;
    }

    public void move(String source, String destination) {
        int sourceX = source.charAt(0) - 'a';
        int sourceY = 8 - (source.charAt(1) - '0');
        int destinationX = destination.charAt(0) - 'a';
        int destinationY = 8 - (destination.charAt(1) - '0');
        if (isValidMove(sourceY, sourceX, destinationY, destinationX)) {
            chessBoard.move(sourceY, sourceX, destinationY, destinationX);
            takeTurn();
        }
    }

    private boolean isValidMove(int sourceY, int sourceX, int destinationY, int destinationX) {
        // out of bound
        if (sourceX < 0 || sourceX >= ChessBoard.GRID_SIZE
                || sourceY < 0 || sourceY >= ChessBoard.GRID_SIZE
                || destinationX < 0 || destinationX >= ChessBoard.GRID_SIZE
                || destinationY < 0 || destinationY >= ChessBoard.GRID_SIZE) {
            return false;
        }

        // no piece at the source
        if (chessBoard.get(sourceY, sourceX) == null) {
            return false;
        }

        // check turn
        if (chessBoard.get(sourceY, sourceX).getColor() != turn) {
            return false;
        }

        // 앞으로만 한 칸씩 - white
        if (chessBoard.get(sourceY, sourceX).getColor() == PieceColor.WHITE &&
                (sourceX != destinationX || destinationY - sourceY != 1)) {
            return false;
        }

        // 앞으로만 한 칸씩 - black
        if (chessBoard.get(sourceY, sourceX).getColor() == PieceColor.BLACK &&
                (sourceX != destinationX || destinationY - sourceY != -1)) {
            return false;
        }

        return true;
    }

    private void takeTurn() {
        if (turn == PieceColor.BLACK) {
            turn = PieceColor.WHITE;
        } else if (turn == PieceColor.WHITE) {
            turn = PieceColor.BLACK;
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
