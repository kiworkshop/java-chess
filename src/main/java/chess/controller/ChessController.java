package chess.controller;

import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();
    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    public void play() {
        ChessService chessService = new ChessService();
        outputView.initPrint();
        String command = START;
        while (!command.equals(END)){
            command = getCommand();
            checkCommadMessage(chessService, command);
        }
    }

    private void checkCommadMessage(ChessService chessService, String command) {
        if(command.equals(START)){
            chessService.start();
        }

        if(command.startsWith(MOVE)) {
            String[] position = command.split(" ");
            chessService.move(position[1],position[2]);
        }

    }

    private static String getCommand() {
        String command = inputView.getUserCommand();
        while(!(command.startsWith(START) || command.equals(END) || command.startsWith(MOVE))){
            outputView.printInCorrectCommandMessage();
            command = inputView.getUserCommand();
        }
        return command;
    }
}
