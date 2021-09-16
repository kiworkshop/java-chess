package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;

import java.util.List;

public interface GameState {

    GameState start();

    GameState move(Piece source, Piece target);

    Turn toggle();

    double status(Team team);

    GameState end();

    List<String> winner(Score score);

    Turn getTurn();

    Board getBoard();
}
