package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.PiecePosition;
import lombok.Getter;

@Getter
public class MovingDirection {
    private PiecePosition sourcePosition;
    private PiecePosition targetPosition;
    private Direction direction;
    private int gap;

    public MovingDirection(PiecePosition sourcePosition, PiecePosition targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        initDirection();
    }

    private void initDirection() {
        int originFile = sourcePosition.getFile().getFilePosition();
        int originRank = sourcePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);
        this.direction = Direction.OTHERS;
        this.gap = 0;
        if(fileGap == 0 && originRank > targetRank){
            this.direction = Direction.DOWN;
            this.gap = rankGap;
        }
        if(fileGap == 0 && originRank < targetRank) {
            this.direction = Direction.UP;
            this.gap = rankGap;
        }
        if(rankGap == 0 && originRank > targetRank){
            this.direction = Direction.LEFT;
            this.gap = fileGap;
        }
        if(rankGap == 0 && originRank < targetRank) {
            this.direction = Direction.RIGHT;
            this.gap = fileGap;
        }
        //좌상
        if (fileGap == rankGap && (targetFile < originFile && targetRank > originRank)) {
            this.direction =  Direction.LEFTUP;
            this.gap = fileGap;
        }
        //우상
        if (fileGap == rankGap && (targetFile > originFile && targetRank > originRank)) {
            this.direction = Direction.RIGHTUP;
            this.gap = fileGap;
        }
        //우하
        if (fileGap == rankGap && (targetFile > originFile && targetRank < originRank)) {
            this.direction = Direction.RIGHTDOWUN;
            this.gap = fileGap;
        }
        //좌하
        if (fileGap == rankGap && (targetFile < originFile && targetRank < originRank)) {
            this.direction = Direction.LEFTDOWUN;
            this.gap = fileGap;
        }
    }
    public boolean isHorizontal(){
        return direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT);
    }
    public boolean isVertical(){
        return direction.equals(Direction.UP) || direction.equals(Direction.DOWN);
    }
    public boolean isDiagonal(){
        return direction.equals(Direction.LEFTUP) || direction.equals(Direction.LEFTDOWUN) ||
                direction.equals(Direction.RIGHTUP) || direction.equals(Direction.RIGHTDOWUN);
    }
}
