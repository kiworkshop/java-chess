package chess.domain.plate;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.team.Camp;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class ChessPlate {
    List<Piece> allPieces = new LinkedList<>();

    public ChessPlate() {
        Camp black = new Camp(Team.BLACK);
        Camp white = new Camp(Team.WHITE);
        allPieces.addAll(black.getPieces());
        allPieces.addAll(white.getPieces());
    }
}
