package chess.service;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.plate.ChessPlate;
import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ChessService {
    private ChessPlate chessPlate = new ChessPlate();

    public ChessPlate start() {
        this.chessPlate = new ChessPlate();
        return chessPlate;
    }

    public boolean move(String sourcePosition, String targetPosition) {
        String fileString = sourcePosition.substring(0, 1);
        File sourceFile = File.findBy(fileString);
        String rankString = sourcePosition.substring(1);
        Rank sourceRank = Rank.findBy(rankString);

        String targetFileString = targetPosition.substring(0, 1);
        File targetFile = File.findBy(targetFileString);
        String targetRankString = targetPosition.substring(1);
        Rank targetRank = Rank.findBy(targetRankString);

        PiecePosition sourcePiecePosition = new PiecePosition(sourceFile, sourceRank);
        PiecePosition targetPiecePosition = new PiecePosition(targetFile, targetRank);


        return chessPlate.move(sourcePiecePosition, targetPiecePosition);
    }

    public double getGameScore(Team team) {

        return chessPlate.getPlate().values().stream()
                .filter(piece -> piece != null)
                .filter(piece -> piece.getTeam().equals(team))
                .mapToDouble(piece -> piece.getScore())
                .sum() - calculatePawnScore(team);
    }

    public double calculatePawnScore(Team team) {
        Map<File, List<String>> pawnAlign =
                chessPlate.getPlate().values().stream()
                        .filter(piece -> piece instanceof Pawn)
                        .filter(piece -> piece.getTeam().equals(team))
                        .collect(Collectors.groupingBy(piece -> piece.getPiecePosition().getFile(),
                                Collectors.mapping(Piece::getName, Collectors.toList())));
        double score = 0;
        for (List a : pawnAlign.values()) {
            if (a.size() >= 2) {
                score = +a.size();
            }
        }
        return score * 0.5;
    }

}
