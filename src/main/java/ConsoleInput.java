import java.util.Scanner;

public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputGameMessage() {
        System.out.print("입력> ");
        scanner.next();
        return "dd";
    }
}
