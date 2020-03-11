import ChessPieces.ChessPieces;

public class ChessGame {
    public ChessGame() {
    }

    public void run() {
        ConsoleOutput.printChessGameNotice();
        String gameMessage = ConsoleInput.inputGameMessage();
        playChessGameTurn(gameMessage);
    }

    private void playChessGameTurn(String gameMessage) {
        if (!gameMessage.equals("start")) {    //TODO 더 명료하게
            return;
        }
        while (gameMessage != "end") {
            ChessPieces chessPieces = ChessPieces.makeInitialSetting();
            ConsoleOutput.printChessBoard(chessPieces);
            ConsoleInput.inputGameMessage();


        }
    }
}
