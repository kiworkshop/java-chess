public class ChessBoardSquare {
    ChessPiece chessPiece;

    public void holdPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public void print() {
        if(chessPiece == null)
            System.out.print(".");
        else
            System.out.print("p");
    }
}
