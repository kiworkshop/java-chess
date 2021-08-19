package chess.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.Finish;
import chess.domain.state.GameState;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.dto.ChessGameDto;

public class ChessGame {
    private GameState gameState;
    private Board board;

    public ChessGameDto startGame() {
        Ready ready = new Ready();
        changeChessGame(ready);
        this.board = ready.getBoard();
        return new ChessGameDto(ready, board.values());
    }

    public ChessGameDto move(Piece source, Piece target) {
        Playing playing = new Playing(Turn.of(source.team()));
        changeChessGame(playing);

        board.move(source.position(), target.position());
        playing.toggle();
        return new ChessGameDto(gameState, board.values());
    }

    public ChessGameDto endGame() {
        Finish finish = new Finish();
        changeChessGame(finish);
        return new ChessGameDto(gameState, board.values());
    }

    private void changeChessGame(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }
}
