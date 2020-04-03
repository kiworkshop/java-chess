package mychess.domain.piece;

public enum PieceType {

    PAWN('♙', '♟'),
    KNIGHT('♘', '♞'),
    ROOK('♖', '♜'),
    BISHOP('♗', '♝'),
    KING('♔', '♚'),
    QUEEN('♕', '♛'),
    NULL('ㅁ', 'ㅁ');

    private char whiteSymbol;
    private char blackSymbol;

    PieceType(char whiteSymbol, char blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public char getWhiteSymbol() {
        return whiteSymbol;
    }

    public char getBlackSymbol() {
        return blackSymbol;
    }
}
