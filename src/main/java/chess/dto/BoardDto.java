package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.board.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private BoardDto() {
    }

    private static final String EMPTY_PIECE = ".";

    public static Map<String, String> of(Board board) {
        Map<String, String> pieceOnPositions = new HashMap<>();

        Position.names()
                .forEach(positionKey -> {
                    String pieceName = findPieceName(positionKey, board);
                    pieceOnPositions.put(positionKey, pieceName);
                });

        return pieceOnPositions;
    }

    private static String findPieceName(String positionKey, Board board) {
        Position position = Position.of(positionKey);

        if (board.isEmpty(position)) {
            return EMPTY_PIECE;
        }

        Piece piece = board.findBy(position);
        return PieceType.findNameBy(piece);
    }
}
