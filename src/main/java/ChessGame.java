public class ChessGame {
    private ChessBoard chessBoard = new ChessBoard();

    public void boardInitialize() {
        chessBoard.initialize();
    }

    public void boardPrint(){
        chessBoard.print();
    }
}
