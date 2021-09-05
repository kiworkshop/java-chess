package chess.service;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.MoveParameters;
import chess.domain.player.Color;
import chess.domain.player.Scores;
import chess.dto.BoardDto;
import chess.exception.ForcedTerminationException;
import chess.exception.ScoresRequestedException;

import java.util.Map;

public class ChessService {

    private final Board board;

    public ChessService() {
        this.board = new Board();
    }

    public void run(Command command) {
        if (command.isStart()) {
            return;
        }

        if (command.isEnd()) {
            throw new ForcedTerminationException();
        }

        if (command.isStatus()) {
            throw new ScoresRequestedException();
        }

        if (command.isMove()) {
            MoveParameters parameters = command.getMoveParameters();
            movePiece(parameters);
        }
    }

    public boolean isGameRunning() {
        return board.isBothKingAlive();
    }

    public boolean isGameFinished() {
        return !isGameRunning();
    }

    public void movePiece(MoveParameters parameters) {
        board.move(parameters);
    }

    public Scores getScores() {
        return board.getScores();
    }

    public Map<String, String> getBoardDto() {
        return BoardDto.of(board);
    }

    public String getCurrentTurnDto() {
        return board.getCurrentTurn().name();
    }

    public String getWinnerDto() {
        Color color = board.getWinner();
        return color.name();
    }
}
