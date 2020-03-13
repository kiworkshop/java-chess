package chess.domain;

import chess.util.ChessPieceFactory;
import chess.dto.MovePositionDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<ChessBoardPosition, ChessPiece> chessBoard = new HashMap<>();

    public ChessBoard() {
        initialize();
    }

    private void initialize() {
        List<ChessPiece> pieces = ChessPieceFactory.makePieces();
        for (ChessPiece piece: pieces) {
            this.chessBoard.put(piece.getChessBoardPosition(), piece);
        }
    }

    // Todo 딱봐도 NPE...
    public void move(MovePositionDto movePositionDto) {
        ChessBoardPosition sourcePosition = movePositionDto.getSourcePosition();
        ChessBoardPosition targetPosition = movePositionDto.getTargetPosition();

        if (!chessBoard.containsKey(sourcePosition)) {
            throw new IllegalArgumentException("source위치에 해당하는 말이 없습니다.");
        }

        ChessPiece sourcePiece = chessBoard.get(sourcePosition);
        ChessPiece targetPiece = chessBoard.get(targetPosition);

        if (chessBoard.containsKey(targetPosition) && targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("같은 팀 말이 있는 곳으로는 이동할 수 없습니다.");
        }

        sourcePiece.move(targetPosition);
        chessBoard.remove(targetPosition);
    }

    // Todo change dto
    public Map<ChessBoardPosition, ChessPiece> getDto() {
        return chessBoard;
    }
}
