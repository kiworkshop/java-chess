package chess.domain;

public class SquareDto {

    private ChessBoardPosition chessBoardPosition;
    private String unicode = "_";

    public SquareDto(ChessBoardPosition chessBoardPosition, String unicode) {
        this.chessBoardPosition = chessBoardPosition;
        this.unicode = unicode;
    }

    public SquareDto(ChessBoardPosition chessBoardPosition) {
        this.chessBoardPosition = chessBoardPosition;
    }

    public ChessBoardPosition getChessBoardPosition() {
        return chessBoardPosition;
    }

    public String getUnicodeIfExists() {
        return unicode;
    }
}
