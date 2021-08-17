package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String HEADER = "> ";

    @Override
    public String getCommand() {
        System.out.println(HEADER + "체스 게임을 시작합니다.");
        System.out.println(HEADER + "게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
