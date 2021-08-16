package chess;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    @DisplayName("체스 위치를 생성한다.")
    void create() {
        //given
        String input = "a1";

        //when
        Position position = new Position(File.A, Rank.ONE);

        //then
        AssertionsForClassTypes.assertThat(position.getFile()).isEqualTo("a");
        AssertionsForClassTypes.assertThat(position.getRank()).isEqualTo(1);
    }
}