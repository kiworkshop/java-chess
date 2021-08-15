package chess.domain.plate;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.team.Camp;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class ChessPlate {
    private static final String BLANK =".";
    private String[][] plate = new String[8][8];

    List<Piece> allPieces = new LinkedList<>();

    public ChessPlate() {
        Camp black = new Camp(Team.BLACK);
        Camp white = new Camp(Team.WHITE);
        allPieces.addAll(black.getPieces());
        allPieces.addAll(white.getPieces());
    }

    public String[][] getPlate() {

        for (int i = 0; i < plate.length; i++) {
            for (int j = 0; j < plate.length; j++) {
                plate[i][j] = ".";
            }
        }

        allPieces.forEach(piece -> {
            int x = piece.getPiecePosition().getRank().getRankPosition()-1;
            int y = piece.getPiecePosition().getFile().getFilePosition()-1;
            plate[x][y] = piece.getDisplayName();
        });
        return plate;
    }

}
