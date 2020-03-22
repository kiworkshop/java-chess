package view.output;

import controller.ChessErrorMessage;
import model.chessDomain.ChessGame;

public interface ChessOutput {
    void outputStartChessGameNotice();
    void outputChessBoard(ChessGame chessGame, ChessErrorMessage errorMessage);
}
