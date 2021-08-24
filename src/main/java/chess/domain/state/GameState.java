package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.game.Score;
import chess.game.Turn;

public interface GameState {

    GameState start();

    GameState moveAndToggleTurn(Piece source, Piece target);

    double status(Team team);

    GameState end();

    String winner(Score score);

    Turn turn();

    Board board();
}
