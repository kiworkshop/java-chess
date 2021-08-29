package chess.dto.web;

import chess.domain.ChessGame;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class TurnDto {

    private TurnDto() {
    }

    public static String of(ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            return WHITE.name();
        }
        return BLACK.name();
    }
}
