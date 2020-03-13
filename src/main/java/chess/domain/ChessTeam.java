package chess.domain;

public enum ChessTeam {
    BLACK(7, 8),
    WHITE(2, 1);

    private static ChessTeam currentTurn = ChessTeam.BLACK;

    private int pawnNthColumn;
    private int specialPiecesNthColumn;

    ChessTeam(int pawnNthColumn, int specialPiecesNthColumn) {
        this.pawnNthColumn = pawnNthColumn;
        this.specialPiecesNthColumn = specialPiecesNthColumn;
    }

    public static void changeTurn() {
        if (currentTurn.equals(ChessTeam.BLACK)) {
            currentTurn = ChessTeam.WHITE;
            return;
        }
        currentTurn = ChessTeam.BLACK;
    }

    public int getPawnNthColumn() {
        return pawnNthColumn;
    }

    public int getSpecialPiecesNthColumn() {
        return specialPiecesNthColumn;
    }

    public static boolean isTurn(ChessTeam chessTeam) {
        return currentTurn.equals(chessTeam);
    }
}
