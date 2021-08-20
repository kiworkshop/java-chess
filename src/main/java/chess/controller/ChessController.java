package chess.controller;

import chess.domain.plate.ChessPlate;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();
    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String REASK = "reAsk";

    public void play() {
        ChessService chessService = new ChessService();
        outputView.initPrint();
        String command = START;
        while (!command.equals(END)){
            command = getCommand();
            if(!command.equals(END)){
                command = checkCommadMessage(chessService, command);
            }
        }
    }

    private String checkCommadMessage(ChessService chessService, String command) {
        if(command.equals(START)){
            playStart(chessService);
            return START;
        }
        if(command.startsWith(MOVE)) {
            return playMove(chessService, command);
        }
        return REASK;
    }

    private String playMove(ChessService chessService, String command) {
        String[] position = command.split(" ");
        String status = chessService.move(position[1],position[2]);
        if (status.equals(MOVE)) {
            outputView.printChessPlate(chessService.getChessPlate());
            return MOVE;
        }
        if(status.equals(REASK)) {
            outputView.printCannotMoveMessage();
            return REASK;
        }
        return END;
    }

    private void playStart(ChessService chessService) {
        ChessPlate chessplate = chessService.start();
        outputView.printChessPlate(chessplate);
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
