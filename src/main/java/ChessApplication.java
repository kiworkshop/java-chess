import controller.ChessGameController;
import view.input.ChessInputConsole;
import view.output.ChessOutputConsole;

public class ChessApplication {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new ChessInputConsole(), new ChessOutputConsole());
        chessGameController.run();
    }
}
