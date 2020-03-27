package chess.model.board;

import chess.model.Team;
import chess.model.postiion.Position;

public interface BoardState {

    boolean isAlly(Position target, Team team);

    boolean isEnemy(Position target, Team team);

    boolean isEmpty(Position target);

    boolean isNotEmpty(Position target);

}
