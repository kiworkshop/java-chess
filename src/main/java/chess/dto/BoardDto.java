package chess.dto;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.PieceType;
import chess.exception.EmptyPositionException;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private BoardDto() {
    }

    private static final String EMPTY_PIECE = ".";

    public static Map<String, String> of(ChessGame chessGame) {
        Map<String, String> pieceOnPositions = new HashMap<>();

        Position.names().forEach(positionKey -> {
            String pieceName = findPieceName(chessGame, positionKey);
            pieceOnPositions.put(positionKey, pieceName);
        });

        return pieceOnPositions;
    }

    private static String findPieceName(ChessGame chessGame, String positionKey) {
        Position position = Position.of(positionKey);

        try {
            Piece piece = chessGame.findPieceBy(position);
            return PieceType.findNameBy(piece);
        } catch (EmptyPositionException e) {
            return EMPTY_PIECE;
        }
    }
}
