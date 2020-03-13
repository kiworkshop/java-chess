import ChessPieces.ChessPiecePosition;
import ChessPieces.ChessPieces;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private ChessPieces chessPieces;

    public ChessGame() {
    }

    public void run() {
        ConsoleOutput.printChessGameNotice();
        String gameMessage;
        do {
            gameMessage = ConsoleInput.inputGameMessage();
            playChessGameTurn(gameMessage);

        } while (true);
    }

    private void playChessGameTurn(String gameMessage) {
        if (gameMessage.equals("end")) {
            System.exit(0);
        }
        if (gameMessage.equals("start")) {    //TODO 더 명료하게
            chessPieces = ChessPieces.makeInitialSetting();
            ConsoleOutput.printChessBoard(chessPieces);
        }
        List<String> splitedMessage = Arrays.asList(gameMessage.split("\\s"));
        if (splitedMessage.get(0).equals("move")) {
            String fromPosition = splitedMessage.get(1);
            String toPosition = splitedMessage.get(2);
            chessPieces.move(ChessPiecePosition.getPositionByString(fromPosition), ChessPiecePosition.getPositionByString(toPosition));
            ConsoleOutput.printChessBoard(chessPieces);
            // TODO 이동 시키기

        }
    }
}
