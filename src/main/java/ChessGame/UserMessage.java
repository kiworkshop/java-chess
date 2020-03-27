package ChessGame;

import ChessGame.ChessPieces.ChessPiecePosition;

import java.util.Arrays;
import java.util.List;

public class UserMessage {
    String command;
    ChessPiecePosition fromPosition;
    ChessPiecePosition toPosition;

    public UserMessage(String command, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        this.command = command;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static UserMessage processInput(String inputGameMessage) {
        List<String> splitedInputGameMessage = Arrays.asList(inputGameMessage.split(" "));
        ChessPiecePosition fromPosition = ChessPiecePosition.getPositionByArray()
        return new UserMessage(splitedInputGameMessage.get(0), splitedInputGameMessage.get(1), splitedInputGameMessage.get(2));



    }
}
