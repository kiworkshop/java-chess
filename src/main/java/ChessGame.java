import ChessPieces.ChessPieces;

public class ChessGame {
    private ChessPieces chessPieces;

    public ChessGame() {
    }

    public void run() {
        ConsoleOutput.printChessGameNotice();
        String gameMessage;
        do {
            gameMessage = ConsoleInput.inputGameMessage();
            playChessGameTurn(gameMessage);

        } while (true);
    }

    private void playChessGameTurn(String gameMessage) {
        if (gameMessage.equals("end")) {
            System.exit(0);
        }
        if (gameMessage.equals("start")) {    //TODO 더 명료하게
            chessPieces = ChessPieces.makeInitialSetting();
            ConsoleOutput.printChessBoard(chessPieces);
            return;
        }
        if (gameMessage.split(" ")[0].equals("move")) {
            ConsoleOutput.printChessBoard(chessPieces);
            // TODO 이동 시키기


        }
    }
}
