package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardInitializer {
    private BoardInitializer() {
    }

    public static Map<Position, Piece> reset() {
        Map<String, Position> newPositions = Position.init();
        Map<Position, Piece> initialChessBoard = new LinkedHashMap<>();
        setupBlankPieces(newPositions, initialChessBoard);
        setupChessPieces(newPositions, initialChessBoard);
        return initialChessBoard;
    }

    private static void setupBlankPieces(Map<String, Position> newPositions, Map<Position, Piece> initialChessBoard) {
        newPositions.values()
                .forEach(position -> initialChessBoard.put(position, Blank.of(position)));
    }

    private static void setupChessPieces(Map<String, Position> newPositions, Map<Position, Piece> initialChessBoard) {
        InitialPieceRepository.init(newPositions).values()
                .forEach(piece -> initialChessBoard.replace(piece.getPosition(), piece));
    }
}
