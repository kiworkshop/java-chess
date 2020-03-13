import java.util.Scanner;

public class ConsoleInput {
    public static final String MESSAGE_INPUT = "입력> ";
    private static Scanner scanner = new Scanner(System.in);

    public static String inputGameMessage() {
        System.out.print(MESSAGE_INPUT);
        System.out.println();
        return scanner.nextLine();
    }
}
