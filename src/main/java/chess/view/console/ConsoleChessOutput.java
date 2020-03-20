package chess.view.console;

import chess.controller.dto.ChessResponse;
import chess.view.ChessOutput;

public class ConsoleChessOutput implements ChessOutput {
    @Override
    public void write(ChessResponse chessResponse) {
        printBoard(chessResponse);
        System.out.println(chessResponse.getMessage());
    }

    private void printBoard(ChessResponse chessResponse) {
        if (chessResponse.getBoard() == null) {
            return;
        }
        System.out.println("대충 보드라 하자");
    }

}
