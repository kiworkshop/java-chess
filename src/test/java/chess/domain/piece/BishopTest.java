package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.groups.Tuple.tuple;

class BishopTest {

    @ParameterizedTest
    @MethodSource("createParameters")
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success(String targetPosition, Tuple expected) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Bishop(Color.WHITE);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).extracting("file", "rank")
                .containsOnly(expected);
    }

    @Test
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target() {
        //given
        Position source = Position.of("c1");
        Position target = Position.of("f5");
        Piece piece = new Bishop(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position position = Position.of("d4");
        Piece bishop = new Bishop(Color.WHITE);
        Collection<Position> expected = Arrays.asList(
                Position.of("a1"), Position.of("b2"), Position.of("c3"), Position.of("e5"),
                Position.of("a7"), Position.of("b6"), Position.of("c5"), Position.of("e3"),
                Position.of("f6"), Position.of("g7"), Position.of("h8"),
                Position.of("f2"), Position.of("g1")
        );

        //when
        Collection<Position> availableAttackPositions = bishop.findAvailableAttackPositions(position);

        //then
        assertThat(availableAttackPositions)
                .hasSize(expected.size())
                .containsAll(expected);
    }

    private static Stream<Arguments> createParameters() {
        return Stream.of(
                Arguments.of("b6", tuple(File.c, Rank.R5)),
                Arguments.of("b2", tuple(File.c, Rank.R3)),
                Arguments.of("f2", tuple(File.e, Rank.R3)),
                Arguments.of("f6", tuple(File.e, Rank.R5))
        );
    }
}
