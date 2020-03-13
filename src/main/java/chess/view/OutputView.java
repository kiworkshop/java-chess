package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Row;
import chess.domain.piece.Movable;

public class OutputView {

    public static void printBoard(Board board) {
        printColumnName();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Movable movable = board.getPawn(column.getName() + row.getValue());
                String position = movable.toString();
                System.out.print(position);
            }
            System.out.print(" " + row.getValue());
            System.out.println();
        }
    }

    private static void printColumnName() {
        for (Column column : Column.values()) {
            System.out.print(" " + column + " ");
        }
        System.out.println();
    }

}
