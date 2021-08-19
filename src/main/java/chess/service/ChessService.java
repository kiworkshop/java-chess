package chess.service;

import chess.domain.piece.*;
import chess.domain.plate.ChessPlate;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ChessService {
    private ChessPlate chessPlate= new ChessPlate();
    public ChessPlate start() {
        this.chessPlate = new ChessPlate();
        return chessPlate;
    }

    public boolean move(String sourcePosition, String targetPosition) {
        String fileString = sourcePosition.substring(0,1);
        File sourceFile = File.findBy(fileString);
        String rankString = sourcePosition.substring(1);
        Rank sourceRank = Rank.findBy(rankString);

        String targetFileString = targetPosition.substring(0,1);
        File targetFile = File.findBy(targetFileString);
        String targetRankString = targetPosition.substring(1);
        Rank targetRank = Rank.findBy(targetRankString);

        PiecePosition sourcePiecePosition = new PiecePosition(sourceFile, sourceRank);
        PiecePosition targetPiecePosition = new PiecePosition(targetFile, targetRank);
        Piece source = chessPlate.getAllPieces().stream()
                .filter(piece -> piece.getPiecePosition().equals(sourcePiecePosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다"));

        return chessPlate.move(sourcePiecePosition, targetPiecePosition);
    }

    public ArrayList<Double> getGameScore() {
        ArrayList<Double> result = new ArrayList<>();
        result.add(chessPlate.getPlate().values().stream()
                .filter(piece -> piece.getTeam().equals(Team.BLACK))
                .mapToDouble(piece -> piece.getScore())
                .sum());

        result.add(chessPlate.getPlate().values().stream()
                .filter(piece -> piece.getTeam().equals(Team.WHITE))
                .mapToDouble(piece -> piece.getScore())
                .sum());

        return result;
    }

}
