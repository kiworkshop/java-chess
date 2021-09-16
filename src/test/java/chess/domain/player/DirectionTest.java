package chess.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"0, 2, NORTH", "0, -2, SOUTH", "2, 0, EAST", "-2, 0, WEST",
            "2, 2, NORTH_EAST", "2, -2, SOUTH_EAST", "-2, 2, NORTH_WEST", "-2, -2, SOUTH_WEST",
            "2, 4, NORTH_EAST_NORTH", "4, 2, NORTH_EAST_EAST", "-2, 4, NORTH_WEST_NORTH", "-4, 2, NORTH_WEST_WEST",
            "2, -4, SOUTH_EAST_SOUTH", "4, -2, SOUTH_EAST_EAST", "-2, -4, SOUTH_WEST_SOUTH", "-4, -2, SOUTH_WEST_WEST"})
    @DisplayName("주어진 좌표로 이동할 수 있는 방향이 존재하는지 확인한다.")
    void matches(int x, int y, Direction direction) {
        //when
        boolean isMatched = direction.matches(x, y);

        //then
        assertThat(isMatched).isTrue();
    }
}
