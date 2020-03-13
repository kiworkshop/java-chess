package chess.view;

public enum AnswerType {

    START,
    END,
    MOVE;

    public static AnswerType of(String name) {
        return valueOf(name.toUpperCase());
    }

}
