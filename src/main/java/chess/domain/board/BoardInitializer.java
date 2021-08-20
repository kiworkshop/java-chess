package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardInitializer {
    private BoardInitializer() {
    }

    public static Map<Position, Piece> reset() {
        Map<Position, Piece> initialChessBoard = new LinkedHashMap<>();
        setupBlankPieces(initialChessBoard);
        setupChessPieces(initialChessBoard);

        return initialChessBoard;
    }

    private static void setupChessPieces(Map<Position, Piece> initialChessBoard) {
        Arrays.stream(InitialPieceRepository.values())
                .forEach(piece -> initialChessBoard.replace(piece.position(), piece.object()));
    }

    private static void setupBlankPieces(Map<Position, Piece> initialChessBoard) {
        Position.all()
                .forEach(position -> initialChessBoard.put(position, Blank.of(position)));
    }
}
