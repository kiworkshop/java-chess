package chess.service;

import chess.domain.piece.File;
import chess.domain.piece.Rank;
import chess.domain.plate.ChessPlate;

public class ChessService {
    public ChessPlate start() {
        return new ChessPlate();
    }

    public void move(String sourcePosition, String targetPosition) {
        String fileString = sourcePosition.substring(0,1);
        File f = File.valueOfString(fileString);
        String rankString = sourcePosition.substring(1);
        Rank r = Rank.findBy(rankString);

    }
}
