package chess.controller.dto;

public class ChessRequest {
    public enum Action {
        START, END, MOVE
    };

    private Action action;
    private String sourcePosition;
    private String destinationPosition;

    private ChessRequest(Action action, String sourcePosition, String destinationPosition) {
        this.action = action;
        this.sourcePosition = sourcePosition;
        this.destinationPosition = destinationPosition;
    }
    public static ChessRequest of(Action action) {
        return new ChessRequest(action, "", "");
    }
    public static ChessRequest of(Action action, String sourcePosition, String destinationPosition) {
        return new ChessRequest(action, sourcePosition, destinationPosition);
    }

    public Action getAction() {
        return action;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getDestinationPosition() {
        return destinationPosition;
    }
}
