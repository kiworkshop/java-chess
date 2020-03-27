package chess.domain.board;

import chess.domain.piece.PieceDto;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardInitializer;
import chess.model.board.BoardState;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessBoard implements Board {

    private Map<Position, PieceState> board;

    private ChessBoard(Map<Position, PieceState> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new ChessBoard(boardInitializer.create());
    }

    @Override
    public void move(Position source, Position target, Turn turn) {
        PieceState pieceState = board.get(source);
        validateSource(pieceState);
        validateTurn(pieceState, turn);
        PieceState newPiece = pieceState.move(target, getBoardState());
        board.remove(source);
        board.put(target, newPiece);
        turn.changeTurn();
    }

    private void validateSource(PieceState pieceState) {
        if (Objects.isNull(pieceState)) {
            throw new IllegalArgumentException("잘못된 위치를 선택하셨습니다.");
        }
    }

    private void validateTurn(PieceState pieceState, Turn turn) {
        if (!turn.sameTurn(pieceState.getTeam())) {
            throw new IllegalArgumentException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    private BoardState getBoardState() {
        Map<Position, PieceDto> boardState = board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> PieceDto.from(entry.getValue())
                ));
        return BoardStateImpl.from(boardState);
    }

    @Override
    public PieceState getPieceState(Position position) {
        return board.get(position);
    }
}
