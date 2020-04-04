package chess.dto;

import chess.domain.ChessBoardSquare;

public class MovePositionDto {

    private ChessBoardSquare sourcePosition;
    private ChessBoardSquare targetPosition;

    private MovePositionDto(ChessBoardSquare sourcePosition, ChessBoardSquare targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public static MovePositionDto of(ChessBoardSquare sourcePosition, ChessBoardSquare targetPosition) {
        return new MovePositionDto(sourcePosition, targetPosition);
    }

    public ChessBoardSquare getSourcePosition() {
        return sourcePosition;
    }

    public ChessBoardSquare getTargetPosition() {
        return targetPosition;
    }
}
