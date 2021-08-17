package chess.service;

import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;
import chess.domain.plate.ChessPlate;

import java.util.Optional;

public class ChessService {
    private static ChessPlate chessPlate= new ChessPlate();
    public ChessPlate start() {
        this.chessPlate = new ChessPlate();
        return chessPlate;
    }

    public void move(String sourcePosition, String targetPosition) {
        String fileString = sourcePosition.substring(0,1);
        File sourceFile = File.valueOfString(fileString);
        String rankString = sourcePosition.substring(1);
        Rank sourceRank = Rank.findBy(rankString);
        PiecePosition sourcePiecePosition = new PiecePosition(sourceFile, sourceRank);
        Piece source = chessPlate.getAllPieces().stream()
                .filter(piece -> piece.getPiecePosition().equals(sourcePiecePosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다"));


    }
}
