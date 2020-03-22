package model.chessDomain;

import controller.ChessErrorMessage;
import model.chessDomain.pieces.PieceColor;
import model.chessDomain.pieces.PieceType;

public class ChessGame {
    private ChessBoard chessBoard;
    private PieceColor turn;

    public void start() {
        chessBoard = new ChessBoard();
        turn = PieceColor.WHITE;
    }

    public ChessErrorMessage move(String source, String destination) {
        ChessErrorMessage errorMessage;

        int sourceX = Character.toLowerCase(source.charAt(0)) - 'a';
        int sourceY = 8 - (source.charAt(1) - '0');
        int destinationX = Character.toLowerCase(destination.charAt(0)) - 'a';
        int destinationY = 8 - (destination.charAt(1) - '0');

        Position sourcePosition = Position.of(sourceX, sourceY);
        Position destinationPosition = Position.of(destinationX, destinationY);

        if ((errorMessage = isValidMove(sourcePosition, destinationPosition)) == ChessErrorMessage.SUCCESS) {
            // king dead
            if (chessBoard.get(destinationPosition) != null
                    && chessBoard.get(destinationPosition).getType() == PieceType.KING) {
                if (chessBoard.get(destinationPosition).getColor() == PieceColor.WHITE) {
                    errorMessage = ChessErrorMessage.WHITE_WIN;
                } else if (chessBoard.get(destinationPosition).getColor() == PieceColor.BLACK) {
                    errorMessage = ChessErrorMessage.BLACK_WIN;
                }
            }

            // move
            chessBoard.move(sourcePosition, destinationPosition);
            takeTurn();
        }
        return errorMessage;
    }

    private ChessErrorMessage isValidMove(Position source, Position destination) {
        // out of bound
        if (source.getX() < 0 || source.getX() >= ChessBoard.GRID_SIZE
                || source.getY() < 0 || source.getY() >= ChessBoard.GRID_SIZE
                || destination.getX() < 0 || destination.getX() >= ChessBoard.GRID_SIZE
                || destination.getY() < 0 || destination.getY() >= ChessBoard.GRID_SIZE) {
            return ChessErrorMessage.INPUT_OUT_OF_BOUND;
        }

        // source == destination
        if (source.getX() == destination.getX() && source.getY() == destination.getY()) {
            return ChessErrorMessage.WRONG_MOVE;
        }

        // no piece at the source
        if (chessBoard.get(source) == null) {
            return ChessErrorMessage.SOURCE_NOT_VALID;
        }

        // check turn
        if (chessBoard.get(source).getColor() != turn) {
            if (turn == PieceColor.WHITE) {
                return ChessErrorMessage.WHITE_TURN;
            } else if (turn == PieceColor.BLACK) {
                return ChessErrorMessage.BLACK_TURN;
            }
        }

        // if piece at the destination, check same color
        if (chessBoard.get(destination) != null
                && chessBoard.get(source).getColor() == chessBoard.get(destination).getColor()) {
            return ChessErrorMessage.WRONG_MOVE;
        }

        // check valid move
        if (!chessBoard.get(source).isValidMove(source, destination, chessBoard.getBoardSnapshot())) {
            return ChessErrorMessage.WRONG_MOVE;
        }

        return ChessErrorMessage.SUCCESS;
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

    public PieceColor getTurn() {
        return turn;
    }
}
