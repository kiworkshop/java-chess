package chess.service;

import chess.domain.command.Command;
import chess.domain.command.MoveParameters;
import chess.domain.game.ChessGame;
import chess.domain.team.Color;
import chess.dto.BoardDto;
import chess.dto.Scores;
import chess.dto.TurnDto;
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

    public TurnDto getCurrentTurnDto() {
        return TurnDto.of(chessGame.getCurrentTurn());
    }

    public String getWinnerDto() {
        Color color = chessGame.getWinner();
        return color.name();
    }
}
