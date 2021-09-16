package chess.domain.player;

import chess.domain.board.File;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("가로, 세로 인자에 해당하는 위치를 반환한다.")
    void from_file_and_rank() {
        //given
        File file = File.A;
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
        File file = File.A;
        Rank rank = Rank.R1;
        String key = file.name() + rank.getIndex();

        //when
        Position position = Position.of(key);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }

    @Test
    @DisplayName("랭크가 동일한지 확인한다.")
    void has_same_rank() {
        // given
        Rank rank = Rank.R1;
        Position position = Position.of("a1");

        // when
        boolean hasSameRank = position.hasSameRank(rank);

        // then
        assertThat(hasSameRank).isTrue();
    }
}
