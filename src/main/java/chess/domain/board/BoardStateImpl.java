package chess.domain.board;

import chess.domain.piece.PieceDto;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.postiion.Position;

import java.util.Map;
import java.util.Objects;

public class BoardStateImpl implements BoardState {

    Map<Position, PieceDto> boardState;

    private BoardStateImpl(Map<Position, PieceDto> boardState) {
        this.boardState = boardState;
    }

    public static BoardStateImpl from(Map<Position, PieceDto> boardState) {
        return new BoardStateImpl(boardState);
    }

    @Override
    public boolean isAlly(Position target, Team team) {
        return Objects.nonNull(getPiece(target)) && team.isAlly(getTeam(target));
    }

    @Override
    public boolean isEnemy(Position target, Team team) {
        return Objects.nonNull(getPiece(target)) && team.isEnemy(getTeam(target));
    }

    @Override
    public boolean isEmpty(Position target) {
        return Objects.isNull(getPiece(target));
    }

    @Override
    public boolean isNotEmpty(Position target) {
        return Objects.nonNull(getPiece(target));
    }

    private PieceDto getPiece(Position target) {
        return boardState.get(target);
    }

    private Team getTeam(Position target) {
        return boardState.get(target).getTeam();
    }
}
