package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;
import chess.dto.console.BoardConsoleDto;
import chess.dto.web.BoardWebDto;

import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class ChessService {

    private final Board board;
    private Color currentTurn;
    private boolean isFinished;

    public ChessService() {
        this.board = new Board();
        this.currentTurn = WHITE;
        this.isFinished = false;
    }

    public boolean isGameFinished() {
        return isFinished;
    }

    public boolean isGameRunning() {
        return !isFinished;
    }

    public BoardConsoleDto getBoardConsoleView() {
        return new BoardConsoleDto(board);
    }

    public Map<String, String> getBoardWebView() {
        return BoardWebDto.of(board);
    }

    public String getCurrentTurn() {
        return currentTurn.name();
    }

    public Status getStatus() {
        return board.getStatus();
    }

    public void movePiece(MoveParameters parameters) {
        board.move(parameters, currentTurn);
        changeTurn();

        if (board.isKingDead()) {
            finish();
        }
    }

    private void changeTurn() {
        if (currentTurn == WHITE) {
            currentTurn = BLACK;
            return;
        }

        if (currentTurn == BLACK) {
            currentTurn = WHITE;
        }
    }

    public void finish() {
        isFinished = true;
    }

    public String getWinner() {
        if (isFinished) {
            Color color = board.getWinner();
            return color.name();
        }
        throw new IllegalStateException("King이 잡히지 않아 승자가 없습니다.");
    }
}
