package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {
    public static Map<Position, Piece> reset() {
        Map<Position, Piece> initialChessBoard = new HashMap<>();
        Arrays.stream(InitialPieceRepository.values())
                .forEach(piece -> initialChessBoard.put(piece.position(), piece.object()));

        return initialChessBoard;
    }

}
