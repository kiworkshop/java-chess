package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.MoveParameters;
import chess.domain.team.Color;
import chess.domain.team.Scores;
import chess.dto.BoardDto;
import chess.exception.ForcedTerminationException;
import chess.exception.ScoresRequestedException;

import java.util.Map;

public class ChessService {

    private final ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
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
        return chessGame.isBothKingAlive();
    }

    public boolean isGameFinished() {
        return !isGameRunning();
    }

    public void movePiece(MoveParameters parameters) {
        chessGame.move(parameters);
    }

    public Scores getScores() {
        return chessGame.getScores();
    }

    public Map<String, String> getBoardDto() {
        return BoardDto.of(chessGame);
    }

    public String getCurrentTurnDto() {
        return chessGame.getCurrentTurn().name();
    }

    public String getWinnerDto() {
        Color color = chessGame.getWinner();
        return color.name();
    }
}
