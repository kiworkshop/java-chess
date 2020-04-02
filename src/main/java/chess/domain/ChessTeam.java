package chess.domain;

public enum ChessTeam {
    WHITE("\u2659"),
    BLACK("\u265F");

    private static ChessTeam currentTurn = ChessTeam.WHITE;

    private String pawnUnicode;

    ChessTeam(String pawnUnicode) {
        this.pawnUnicode = pawnUnicode;
    }


    public static void changeTurn() {
        if (currentTurn.equals(ChessTeam.BLACK)) {
            currentTurn = ChessTeam.WHITE;
            return;
        }
        currentTurn = ChessTeam.BLACK;
    }

    public static boolean isTurn(ChessTeam chessTeam) {
        return currentTurn.equals(chessTeam);
    }

    public String getPawnUnicode() {
        return pawnUnicode;
    }
}
