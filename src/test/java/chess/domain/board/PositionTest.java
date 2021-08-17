package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("가로, 세로 인자에 해당하는 위치를 반환한다.")
    void from_file_and_rank() {
        //given
        File file = File.a;
        Rank rank = Rank.R1;

        //when
        Position position = Position.from(file, rank);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }

    @Test
    @DisplayName("문자열 키에 해당하는 위치를 반환한다.")
    void from_key() {
        //given
        File file = File.a;
        Rank rank = Rank.R1;
        String key = file.name() + rank.getIndex();

        //when
        Position position = Position.from(key);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }
}
