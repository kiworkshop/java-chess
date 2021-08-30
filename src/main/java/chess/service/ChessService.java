package chess.service;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.command.MoveParameters;
import chess.dto.web.BoardDto;
import chess.dto.web.TurnDto;

import java.util.Map;

public class ChessService {

    private final ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
    }

    public Map<String, String> getBoardView() {
        Board board = chessGame.getBoard();
        return BoardDto.of(board);
    }

    public String getCurrentTurn() {
        return TurnDto.of(chessGame);
    }

    public Status getStatus() {
        return chessGame.getStatus();
    }

    public boolean isGameFinished() {
        return chessGame.isFinished();
    }

    public void movePiece(MoveParameters moveParameters) {
        chessGame.move(moveParameters);
    }
}
