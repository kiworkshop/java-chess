package mychess.view;

import mychess.domain.Board;
import mychess.domain.Position;
import mychess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printBoard(Board board) {
        System.out.println();

        int lineNumber = Position.MAX_HEIGHT_INDEX;
        Map<Position, Piece> pieces = board.getPieces();
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            System.out.print(piece.getSymbol());
            if (position.isEndOfLine()) {
                System.out.print("   " + lineNumber);
                System.out.println();
                lineNumber--;
            }
        }

        System.out.println();
        System.out.println("A B C D E F G H");
    }
}
