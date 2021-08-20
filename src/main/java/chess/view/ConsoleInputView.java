package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String getCommand() {
        String input = scanner.nextLine();
        validateNull(input);
        return input.trim();
    }

    private void validateNull(final String input) {
        if (input == null) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
