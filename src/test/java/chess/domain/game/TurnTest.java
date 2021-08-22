package chess.domain.game;

import chess.domain.board.Team;
import chess.game.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("기물을 이동하면 상대편의 턴으로 전환한다.")
    void turn_toggle() {
        //given
        Turn turn = Turn.of(Team.WHITE);

        //when
        turn = turn.toggle();

        //then
        assertThat(turn.team()).isEqualTo(Team.BLACK);
    }
}
