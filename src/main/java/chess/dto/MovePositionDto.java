package chess.dto;

import chess.domain.ChessBoardPosition;

public class MovePositionDto {

    private ChessBoardPosition sourcePosition;
    private ChessBoardPosition targetPosition;

    private MovePositionDto(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public static MovePositionDto of(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return new MovePositionDto(sourcePosition, targetPosition);
    }

    public ChessBoardPosition getSourcePosition() {
        return sourcePosition;
    }

    public ChessBoardPosition getTargetPosition() {
        return targetPosition;
    }
}
