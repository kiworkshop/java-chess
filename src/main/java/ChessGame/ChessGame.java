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
        UserMessage userMessage;
        do {
            userMessage = UserMessage.processInput(ConsoleInput.inputGameMessage());
            playChessGameTurn(userMessage);
        } while (true);
    }

    private void playChessGameTurn(UserMessage userMessage) {
        if (userMessage.getCommand().equals("end")) {
            System.exit(0);
        }
        if (userMessage.getCommand().equals("start")) {    //TODO 더 명료하게
            chessPieces = ChessPieces.makeInitialSetting();
            playerNumber = PlayerNumber.PLAYER_NUMBER_ONE;
        }
        if (userMessage.getCommand().equals("move")) {
            tryMove(userMessage);
        }
        ConsoleOutput.printChessGameTurn(playerNumber);
        ConsoleOutput.printChessBoard(chessPieces);
    }

    private void tryMove(UserMessage userMessage) {
        try {
            chessPieces.move(playerNumber, userMessage.getFromPosition(), userMessage.getToPosition());
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
