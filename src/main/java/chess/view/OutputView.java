package chess.view;

import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;

import java.util.Arrays;
import java.util.Map;

public class OutputView {

    public void printChessPlate(Map<PiecePosition, Piece> plate) {
        for (Map.Entry<PiecePosition, Piece> entry: plate.entrySet()) {
            if(entry.getKey().getFile().equals(File.H)){
                System.out.println(entry.getValue().getDisplayName());
            }
            System.out.print(entry.getValue().getDisplayName()+", ");
        }
    }
}
