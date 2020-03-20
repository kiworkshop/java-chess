package mychess.view.console;

import mychess.controller.dto.ChessResponse;
import mychess.domain.Position;
import mychess.domain.piece.Piece;
import mychess.view.ChessOutput;

import java.util.Map;

public class ConsoleChessOutput implements ChessOutput {

    @Override
    public void write(ChessResponse chessResponse) {
        System.out.println();
        System.out.println(chessResponse.getMessage());
        printBoard(chessResponse);
        printCurrentPlayer(chessResponse);
        System.out.println();
    }

    private void printCurrentPlayer(ChessResponse chessResponse) {
        System.out.println(chessResponse.getCurrentPlayer().getColor() + "의 차례입니다.");
    }

    private void printBoard(ChessResponse chessResponse) {
        if (chessResponse.getBoard() == null) {
            return;
        }

        int lineNumber = Position.MAX_HEIGHT_INDEX;
        Map<Position, Piece> pieces = chessResponse.getBoard().getPieces();
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
        System.out.println("A B C D E F G H");
    }
}
