package ChessGame;

import ChessGame.ChessPieces.ChessPiecePosition;

import java.util.Arrays;
import java.util.List;

public class UserMessage {
    String command;
    ChessPiecePosition fromPosition;
    ChessPiecePosition toPosition;

    public ChessPiecePosition getFromPosition() {
        return fromPosition;
    }

    public ChessPiecePosition getToPosition() {
        return toPosition;
    }

    public String getCommand() {
        return command;
    }

    public UserMessage(String command, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        this.command = command;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static UserMessage processInput(String inputGameMessage) {
        List<String> splitedInputGameMessage = Arrays.asList(inputGameMessage.split(" "));
        return new UserMessage(splitedInputGameMessage.get(0), ChessPiecePosition.getPositionByString(splitedInputGameMessage.get(1)), ChessPiecePosition.getPositionByString(splitedInputGameMessage.get(2)));
    }
}
