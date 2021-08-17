package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @CsvSource({"1,0,EAST", "-1,0,WEST", "0,1,NORTH", "0,-1,SOUTH", "1,1,NORTH_EAST", "1,-1,SOUTH_EAST", "-1,1,NORTH_WEST", "-1,-1,SOUTH_WEST"})
    @DisplayName("방향을 찾는다.")
    void find_direction(int fileGap, int rankGap, Direction expected) {
        //given, when
        Direction direction = Direction.of(fileGap, rankGap);

        //then
        assertThat(direction).isEqualTo(expected);
    }
}
