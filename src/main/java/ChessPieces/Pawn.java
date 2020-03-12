package ChessPieces;

public class Pawn implements ChessPiece {
    public static final String PRINT_CODE_WHITE = "♙";
    public static final String PRINT_CODE_BLACK = "♟";
    int playerNumber;
    String printCode;
    ChessPiecePosition position;

    public Pawn(int playerNumber, ChessPiecePosition position) {
        this.playerNumber = playerNumber;
        this.position = position;
        setPrintCode();
    }

    public static Pawn setPiece(int playerNumber, ChessPiecePosition position) {
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
    public ChessPiecePosition getPosition() {
        return position;
    }

    public void move(ChessPiecePosition toPosition) {
        if (this.playerNumber == 1) {
            if (toPosition.getX() + 1 ==) {    //TODO .get으로 가져오지 말고  position의 객체를 만들어서 가져오자 ㅠ


            }
            if (toPosition.get(1).equals()) {

            }

        }
    }
