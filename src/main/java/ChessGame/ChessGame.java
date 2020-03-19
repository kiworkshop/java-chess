package ChessGame;

import ChessGame.ChessPieces.ChessPiecePosition;
import ChessGame.ChessPieces.ChessPieces;
import ChessGame.Exception.*;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private ChessPieces chessPieces;
    private PlayerNumber playerNumber = PlayerNumber.PLAYER_NUMBER_ONE;

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
            tryMove(splitedMessage);
        }
        ConsoleOutput.printChessGameTurn(playerNumber);
        ConsoleOutput.printChessBoard(chessPieces);
    }

    private void tryMove(List<String> splitedMessage) {
        String fromPosition = splitedMessage.get(1);
        String toPosition = splitedMessage.get(2);
        try {
            chessPieces.move(playerNumber, ChessPiecePosition.getPositionByString(fromPosition), ChessPiecePosition.getPositionByString(toPosition));
            playerNumber = playerNumber.next();
        } catch (CannotJumptException e) {    //TODO 못배운 놈이라 예외처리 어떻게하는지 모르겠음 좋은 practice가 있나?
            ConsoleOutput.printCannotJumpExceptionMessage();
        } catch (NotRightMoveException e) {
            ConsoleOutput.printNotRightMoveExceptionMessage();
        } catch (NotYourTurnException e) {
            ConsoleOutput.printNotYourTurnExceptionMessage();
        } catch (SamePositionException e) {
            ConsoleOutput.printSamePositionExceptionMessage();
        } catch (TakenPositionException e) {
            ConsoleOutput.printTakenPositionExceptionMessage();
        } catch(NoPieceToMoveException e) {
            ConsoleOutput.printNoPieceToMoveException();
        } catch (GameOverException e) {
            ConsoleOutput.printGameOverMessage(playerNumber);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
