package chess.domain.plate;

import chess.domain.RankComparator;
import chess.domain.piece.*;
import chess.domain.team.Camp;
import lombok.Getter;

import java.util.*;

@Getter
public class ChessPlate {
    private static final String BLANK =".";
    private Map<PiecePosition, Piece> plate = new HashMap<>();
    private List<Piece> allPieces = new LinkedList<>();

    public ChessPlate() {
        Camp black = new Camp(Team.BLACK);
        Camp white = new Camp(Team.WHITE);
        allPieces.addAll(black.getPieces());
        allPieces.addAll(white.getPieces());
    }

    public Map<PiecePosition, Piece> getPlate() {
        RankComparator rankComparator = new RankComparator();

        for (Rank rank: Rank.values()) {
            for (File file : File.values()) {
              plate.put(new PiecePosition(file, rank), new Piece(".",Team.BLACK, new PiecePosition(file,rank)));
            }
        }
        allPieces.forEach(piece -> {
            plate.put(piece.getPiecePosition(), piece);
        });

        return plate;
    }
}
