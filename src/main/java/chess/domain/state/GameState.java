package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.game.Turn;

public interface GameState {

    GameState start();

    GameState move(Position source, Position target);

    GameState end();

    Turn turn();

    Board getBoard();

}
