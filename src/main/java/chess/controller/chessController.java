package chess.controller;

import chess.domain.plate.ChessPlate;
import chess.view.OutputView;

public class chessController {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        ChessPlate chessPlate = new ChessPlate();
        outputView.printChessPlate(chessPlate.getPlate());
    }
}
