package chess.model.board;

import chess.model.Team;
import chess.model.postiion.Position;

public interface BoardState {

    boolean isSameTeam(Position target, Team team);

    boolean isEmpty(Position target);

    boolean isNotEmpty(Position target);

}
