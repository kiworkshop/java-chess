package ChessGame;

import ChessGame.ChessPieces.ChessPiecePosition;
import ChessGame.ChessPieces.ChessPieces;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private ChessPieces chessPieces;
    private PlayerNumber playerNumber;

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
            playerNumber = PlayerNumber.PLAYER_NUMBER_ONE;
        }
        List<String> splitedMessage = Arrays.asList(gameMessage.split("\\s"));
        if (splitedMessage.get(0).equals("move")) {
            String fromPosition = splitedMessage.get(1);
            String toPosition = splitedMessage.get(2);
            try {
                chessPieces.move(playerNumber, ChessPiecePosition.getPositionByString(fromPosition), ChessPiecePosition.getPositionByString(toPosition));
                playerNumber = playerNumber.next();
            } catch (Exception ignored) {
            }
        }
        ConsoleOutput.printChessBoard(chessPieces);
    }
}
