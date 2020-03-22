package view.output;

import controller.ChessMoveResponse;
import model.chessDomain.ChessGame;

public interface ChessOutput {
    void outputStartChessGameNotice();
    void outputChessBoard(ChessGame chessGame, ChessMoveResponse errorMessage);
}
