package chess.controller;

import chess.domain.piece.Team;
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
    public static final String STATUS = "status";

    public void play() {
        ChessService chessService = new ChessService();
        outputView.initPrint();
        String command = START;
        while (!command.equals(END)){
            command = getUserCommand();
            checkCommadMessage(chessService, command);
            gameOverIfKingDead(chessService);
        }
    }

    private void checkCommadMessage(ChessService chessService, String command) {
        if(command.equals(START)){
            ChessPlate chessplate = chessService.start();
            outputView.printChessPlate(chessplate);
        }

        if(command.startsWith(MOVE)) {
            String[] position = command.split(" ");
            boolean isMoved = chessService.move(position[1],position[2]);
            if(isMoved) {
                outputView.printChessPlate(chessService.getChessPlate());
            }else {
                outputView.printCannotMoveMessage();
            }
        }

        if(command.equals(STATUS)) {
            printResult(chessService);
        }

    }

    private static String getUserCommand() {
        String command = inputView.getUserCommand();
        while(!(command.startsWith(START) || command.equals(END) || command.startsWith(MOVE) || command.equals(STATUS))){
            outputView.printInCorrectCommandMessage();
            command = inputView.getUserCommand();
        }
        return command;
    }

    private void printResult(ChessService chessService) {
        double blackScore = chessService.getGameScore(Team.BLACK);
        double whiteScore = chessService.getGameScore(Team.WHITE);

        outputView.printScoresOfTwoTeams(blackScore, whiteScore);
        outputView.printWinner(blackScore, whiteScore);
    }

    private void gameOverIfKingDead(ChessService chessService) {
        if(chessService.isKingDead(Team.BLACK)) {
            outputView.printBlackKingDeadMessage();
            System.exit(0);
        }
        if(chessService.isKingDead(Team.WHITE)) {
            outputView.printWhiteKingDeadMessage();
            System.exit(0);
        }
    }
}
