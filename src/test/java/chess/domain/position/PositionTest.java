package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("체스 위치를 생성한다.")
    void create() {
        //given
        String input = "a1";

        //when
        Position position = new Position(File.A, Rank.ONE);

        //then
        assertThat(position.getFile()).isEqualTo("a");
        assertThat(position.getRank()).isEqualTo(1);
    }
}