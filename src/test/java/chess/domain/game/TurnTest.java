package chess.domain.game;

import chess.domain.team.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {

    @Test
    @DisplayName("플레이 차례인 플레이어를 반환한다.")
    void player_turn() {
        // given
        Turn turn = Turn.initialTurn();

        // when
        turn.next();
        Player player = turn.player();

        // then
        assertThat(player.name()).isEqualTo("BLACK");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("플레이어가 여러 명일 때 플레이 차례인 플레이어를 반환한다.")
    void player_turn_multiple(int count) {
        // given
        List<String> names = Arrays.asList("w1", "b1", "w2", "b2");
        Map<Color, List<Player>> players = new EnumMap<>(Color.class);
        players.put(Color.WHITE, Arrays.asList(Player.of(names.get(0)), Player.of(names.get(2))));
        players.put(Color.BLACK, Arrays.asList(Player.of(names.get(1)), Player.of(names.get(3))));
        Turn turn = Turn.initialTurn(players);

        // when
        for (int i = 0; i < count; i++) {
            turn.next();
        }
        Player player = turn.player();

        // then
        assertThat(player.name()).isEqualTo(names.get(count % 4));
    }
}
