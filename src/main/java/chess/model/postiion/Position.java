package chess.model.postiion;

import chess.model.MovingDirection;

public interface Position {

    Position moveByDirection(MovingDirection movingDirection);

    boolean isBoundary();

    File getFile();

    Rank getRank();

    int fileDifference(Position target);

    int rankDifference(Position target);
}
