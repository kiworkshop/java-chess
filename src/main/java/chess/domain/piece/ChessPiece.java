package chess.domain.piece;

import chess.exception.MovingToAllyException;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.piece.PieceState;
import chess.model.piece.PieceType;
import chess.model.postiion.Position;

public abstract class ChessPiece implements PieceState {

    private final PieceType pieceType;
    protected final Position position;
    protected final Team team;

    ChessPiece(PieceType pieceType, Position position, Team team) {
        this.pieceType = pieceType;
        this.position = position;
        this.team = team;
    }

    @Override
    public PieceState move(Position target, BoardState boardState) {
        validateMovingPolicy(target, boardState);
        validateTarget(target, boardState);
        return movedPieceState(target);
    }

    private void validateTarget(Position target, BoardState boardState) {
        if (boardState.isAlly(target, team)) {
            throw new MovingToAllyException();
        }
    }

    protected abstract void validateMovingPolicy(Position target, BoardState boardState);

    protected abstract PieceState movedPieceState(Position target);

    public String getFigure() {
        return pieceType.getFigure(team);
    }
}
