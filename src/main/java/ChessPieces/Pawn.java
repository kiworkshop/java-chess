package ChessPieces;

import java.util.List;

public class Pawn implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♙";
    public static final String PRINT_CODE_BLACK = "♟";
    int playerNumber;
    String printCode;
    List<Integer> position;

    public Pawn(int playerNumber, List<Integer> position) {
        this.playerNumber = playerNumber;
        this.position = position;
        setPrintCode();
    }

    public static Pawn setPiece(int playerNumber, List<Integer> position) {
        return new Pawn(playerNumber, position);
    }

    private void setPrintCode() {
        if (playerNumber == 1) {
            printCode = PRINT_CODE_WHITE;
        } else {
            printCode = PRINT_CODE_BLACK;
        }
    }

    @Override
    public String getPrintCode() {
        return printCode;
    }

    @Override
    public List<Integer> getPosition() {
        return position;
    }
}
