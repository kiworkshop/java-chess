package chess.domain.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern pattern = Pattern.compile("move [a-h]{1}[1-8]{1} [a-h]{1}[1-8]{1}");

    private InputView() {
    }

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static String[] parseMoveInput(String input) {
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return input.replace("move ", "").split(" ");
        }
        throw new IllegalArgumentException("\"move b2 b3\" 와 같이 입력해 주세요");
    }
}
