package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Scores;
import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;
import chess.dto.BoardDto;

import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class ChessService {

    private final Board board;
    private Color currentTurn;

    public ChessService() {
        this.board = new Board();
        this.currentTurn = WHITE;
    }

    public boolean isGameRunning() {
        return board.isBothKingAlive();
    }

    public void movePiece(MoveParameters parameters) {
        board.move(parameters, currentTurn);
        changeTurn();
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

    public Scores getScores() {
        return board.getScores();
    }

    public Map<String, String> getBoardDto() {
        return BoardDto.of(board);
    }

    public String getCurrentTurnView() {
        return currentTurn.name();
    }

    public String getWinnerView() {
        if (board.isBothKingAlive()) {
            throw new IllegalStateException("King이 잡히지 않아 승자가 없습니다.");
        }

        Color color = board.getWinner();
        return color.name();
    }
}
