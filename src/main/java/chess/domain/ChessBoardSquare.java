package chess.domain;

import jdk.internal.jline.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardSquare {

    // Todo to enum
    public static final Map<String, Integer> alphabetNthRowPair = new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
            put("d", 4);
            put("e", 5);
            put("f", 6);
            put("g", 7);
            put("h", 8);
    }};

    private final ChessBoardPosition chessBoardPosition;
    private @Nullable ChessPiece chessPiece;

    public ChessBoardSquare(ChessBoardPosition chessBoardPosition, ChessPiece chessPiece) {
        this.chessBoardPosition = chessBoardPosition;
        this.chessPiece = chessPiece;
    }

    public ChessBoardSquare(ChessBoardPosition chessBoardPosition) {
        this.chessBoardPosition = chessBoardPosition;
    }

    private static int parseAlphabetToNthRow(String alphabet) {
        if (!alphabetNthRowPair.containsKey(alphabet)) {
            throw new IllegalArgumentException("move 명령어의 알파벳은 a~h까지 입니다.");
        }
        return alphabetNthRowPair.get(alphabet);
    }

    public SquareDto toDto() {
        try {
            return new SquareDto(chessBoardPosition, chessPiece.getUnicode());
        } catch (NullPointerException e) {
            return new SquareDto(chessBoardPosition);
        }
    }
}
