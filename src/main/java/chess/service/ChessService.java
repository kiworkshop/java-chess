package chess.service;

import chess.controller.dto.MoveParams;
import chess.domain.ChessGame;
import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Row;
import chess.domain.piece.Movable;

public class ChessService {

    ChessGame chessGame;

    public void start() {
        chessGame = new ChessGame();
    }

    public void end() {
    }

    public void move(MoveParams moveParams) {
        ChessBoard chessBoard = chessGame.getChessBoard();
        chessBoard.move(moveParams.getSource(), moveParams.getDestination());
    }

    public String parseBoardToString() {
        ChessBoard board = chessGame.getChessBoard();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getColumnName());
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Movable movable = board.getMovable(column.getName() + row.getValue());
                String movableString = movable.toString();
                stringBuilder.append(movableString);
            }
            stringBuilder.append(" " + row.getValue());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getColumnName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Column column : Column.values()) {
            stringBuilder.append(" " + column.getName() + " ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
