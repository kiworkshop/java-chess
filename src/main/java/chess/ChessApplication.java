package chess;

import chess.controller.ChessController;
import chess.domain.plate.ChessPlate;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
//        ChessController chessController = new ChessController();
//        chessController.play();
        OutputView outputView = new OutputView();
        outputView.printChessPlate(new ChessPlate());
    }
}

