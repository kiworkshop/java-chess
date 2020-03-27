package chess.domain.board;

import chess.model.board.BoardInitializer;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.HashMap;
import java.util.Map;

public class EnumRepositoryBoardInitializer implements BoardInitializer {

    @Override
    public Map<Position, PieceState> create() {
        Map<Position, PieceState> initialBoard = new HashMap<>();
        for (InitialPieceRepository pieceRepository : InitialPieceRepository.values()) {
            initialBoard.put(pieceRepository.getPosition(), pieceRepository.getInitialPiece());
        }
        return initialBoard;
    }
}
