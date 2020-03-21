package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

import java.time.LocalDateTime;

public class ChessGame {
    private ChessBoard chessBoard = new ChessBoard();
    private int turnCount;
    private Player whoseTurn = Player.WHITE;
    private LocalDateTime startAt = LocalDateTime.now();
    private LocalDateTime endAt = LocalDateTime.MAX;

    public LocalDateTime end() {
        endAt = LocalDateTime.now();
        return endAt;
    }

    public void move(Position source, Position destination) {
        chessBoard.move(source, destination);
    }
}
