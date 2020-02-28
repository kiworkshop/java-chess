import java.util.Arrays;

public class ChessBoard {

    ChessBoardSquare[][] chessBoardSquares = new ChessBoardSquare[8][8];

    public void initialize() {
        initializeChessBoardSquares();
        initializeWhitePieces();
        initializeBlackPieces();
    }

    private void initializeChessBoardSquares() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                chessBoardSquares[i][j] = new ChessBoardSquare();
            }
        }
    }

    private void initializeWhitePieces() {
        for(int i=0; i<8; i++){
            for(int j=0; j<2; j++){
                chessBoardSquares[i][j].holdPiece(new ChessPiece());
            }
        }
    }

    private void initializeBlackPieces() {
        for(int i=0; i<8; i++){
            for(int j=6; j<8; j++){
                chessBoardSquares[i][j].holdPiece(new ChessPiece());
            }
        }
    }

    public void print() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                chessBoardSquares[i][j].print();
            }
            System.out.println();
        }
    }
}
