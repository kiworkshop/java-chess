package chess.domain.piece;

import lombok.Getter;

@Getter
public enum Direction {
    UP(0,1),
    DOWN(0, -1),
    RIGHT(1,0),
    LEFT(-1,0),
    RIGHTUP(1,1),
    LEFTUP(-1,1),
    RIGHTDOWUN(1,-1),
    LEFTDOWUN(-1,-1),
    OTHERS(0,0);


    private int fileMoveCount;
    private int rankMoveCount;

    Direction(int fileMoveCount, int rankMoveCount) {
        this.fileMoveCount = fileMoveCount;
        this.rankMoveCount = rankMoveCount;
    }
}
