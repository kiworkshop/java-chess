import controller.ChessGameController;
import view.input.ChessInputConsole;
import view.output.ChessOutputConsole;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new ChessInputConsole(), new ChessOutputConsole());
        chessGameController.run();
    }
}
