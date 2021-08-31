package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.command.MoveParameters;
import chess.domain.piece.Color;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class ChessGame {

    private final Board board = new Board();
    private Color currentTurn = WHITE;
    private boolean isFinished = false;

    public ChessGame() {
    }

    public void move(final MoveParameters moveParameters) {
        board.move(moveParameters, currentTurn);
        changeTurn();

        if (board.isKingDead()) {
            end();
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

    public void end() {
        isFinished = true;
    }

    public Status getStatus() {
        return board.getStatus();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return currentTurn.isWhite();
    }

    public boolean isRunning() {
        return !isFinished;
    }
}
