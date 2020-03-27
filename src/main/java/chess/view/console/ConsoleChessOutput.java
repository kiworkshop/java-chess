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
        System.out.print(chessResponse.getBoard());
    }

}
