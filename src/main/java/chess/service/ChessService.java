package chess.service;

import chess.domain.piece.*;
import chess.domain.plate.ChessPlate;
import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Getter
public class ChessService {
    private ChessPlate chessPlate= new ChessPlate();
    public ChessPlate start() {
        this.chessPlate = new ChessPlate();
        return chessPlate;
    }

    public String move(String sourcePosition, String targetPosition) {
        PiecePosition sourcePiecePosition = getPositionBy(sourcePosition);
        PiecePosition targetPiecePosition = getPositionBy(targetPosition);

        return chessPlate.move(sourcePiecePosition, targetPiecePosition);
    }
    private PiecePosition getPositionBy(String sourcePosition){
        String fileString = sourcePosition.substring(0,1);
        File sourceFile = File.findBy(fileString);
        String rankString = sourcePosition.substring(1);
        Rank sourceRank = Rank.findBy(rankString);
        return new PiecePosition(sourceFile, sourceRank);
    }
    public double getGameScore(Team team) {

        return chessPlate.getPlate().values().stream()
                .filter(piece -> piece != null)
                .filter(piece -> piece.getTeam().equals(team))
                .mapToDouble(piece -> piece.getScore())
                .sum() - calculatePawnScore(team);
    }

    public double calculatePawnScore(Team team){
        Map<File, List<String >> pawnAlign =
                chessPlate.getPlate().values().stream()
                        .filter(piece -> piece instanceof Pawn)
                        .filter(piece -> piece.getTeam().equals(team))
                        .collect(Collectors.groupingBy(piece -> piece.getPiecePosition().getFile(),
                                Collectors.mapping(Piece::getName, Collectors.toList())));
        double score = 0;
        for (List a : pawnAlign.values()) {
            if(a.size()>=2){
                score=+a.size();
            }
        }
        return score*0.5;
    }

}
