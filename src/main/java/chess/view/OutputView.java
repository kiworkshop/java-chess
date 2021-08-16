package chess.view;

import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;
import chess.domain.plate.ChessPlate;

import java.util.Map;

public class OutputView {

    public void printChessPlate(ChessPlate chessPlate) {
        for (Rank rank : Rank.values()) {
            for(File file : File.values()) {
                System.out.print(chessPlate.getPlate().get(new PiecePosition(file, rank)).getDisplayName());
            }

            System.out.println();
        }
    }
}
