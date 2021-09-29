package chess.exception;

public class ForcedTerminationException extends RuntimeException {

    private static final String message = "사용자 입력에 의해 게임이 강제 종료되었습니다.";

    public ForcedTerminationException() {
        super(message);
    }
}
