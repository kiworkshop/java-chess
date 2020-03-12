package controller;

import model.chessDomain.ChessGame;
import view.input.ChessInput;
import view.output.ChessOutput;

public class ChessGameController {
    private ChessInput chessInput;
    private ChessOutput chessOutput;
    private ChessGame chessGame = new ChessGame();

    public ChessGameController(ChessInput chessInput, ChessOutput chessOutput) {
        this.chessInput = chessInput;
        this.chessOutput = chessOutput;
    }

    public void run() {
        chessOutput.printStartChessGame();

        while(true) {
            String userInput = chessInput.requestUserInput();
            if (userInput.equalsIgnoreCase("start")) {
                chessGame.start();

            } else if (userInput.equalsIgnoreCase("end")) {
                break;

            } else if (userInput.startsWith("move")) {
                chessGame.move(userInput.split(" ")[1], userInput.split(" ")[2]);

            } else {
                // invalid input
            }

            chessOutput.printChessBoard(chessGame.getChessBoard());
        }
    }
}
