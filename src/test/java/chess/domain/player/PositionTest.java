package chess.domain.player;

import chess.domain.board.File;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

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
        Position position = Position.of(key);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }

    @Test
    @DisplayName("거쳐가는 모든 위치를 반환한다.")
    void find_passing_positions() {
        // given
        Position source = Position.of("d4");
        Position target = Position.of("d7");
        MoveCoordinate moveCoordinate = MoveCoordinate.NORTH;

        // when
        Set<Position> positions = source.findPassingPositions(target, moveCoordinate);

        // then
        assertThat(positions)
                .hasSize(2)
                .containsExactlyInAnyOrder(Position.of("d5"), Position.of("d6"));
    }

    @ParameterizedTest
    @MethodSource("createParams")
    @DisplayName("이동 가능한 위치를 반환한다.")
    void find_available_positions(boolean isFinite, Collection<Position> expected) {
        // given
        Position position = Position.of("d4");
        MoveCoordinate moveCoordinate = MoveCoordinate.EAST;

        // when
        Collection<Position> positions = position.findAvailablePositions(moveCoordinate, isFinite);

        // then
        assertThat(positions)
                .hasSize(expected.size())
                .containsAll(expected);
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

    private static Stream<Arguments> createParams() {
        return Stream.of(
                Arguments.of(false, Arrays.asList(Position.of("e4"), Position.of("f4"), Position.of("g4"), Position.of("h4"))),
                Arguments.of(true, Collections.singletonList(Position.of("e4")))
        );
    }
}
