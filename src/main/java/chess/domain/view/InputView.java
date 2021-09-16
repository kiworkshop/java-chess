package chess.domain.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern MOVE_PATTERN = Pattern.compile("move [a-h][1-8] [a-h][1-8]");

    private InputView() {
    }

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static String[] parseMoveInput(String input) {
        Matcher matcher = MOVE_PATTERN.matcher(input);
        if (matcher.find()) {
            return input.replace("move ", "").split(" ");
        }
        throw new IllegalArgumentException("\"move b2 b3\" 와 같이 입력해 주세요");
    }
}
