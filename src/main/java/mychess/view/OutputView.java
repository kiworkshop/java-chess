package mychess.view;

import mychess.domain.Board;
import mychess.domain.Piece;
import mychess.domain.Position;

import java.util.Map;

public class OutputView {

    public void printBoard(Board board) {
        Map<Position, Piece> pieces = board.getPieces();

        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            System.out.print('\u265F');
            if (position.isEndOfRank()) System.out.println();
        }
    }
}
