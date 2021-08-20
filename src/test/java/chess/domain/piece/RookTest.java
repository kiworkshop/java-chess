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

public class RookTest {

    @ParameterizedTest
    @MethodSource("createParameters")
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success(String targetPosition, Tuple expected) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Rook(Color.WHITE);

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
        Piece piece = new Rook(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position position = Position.of("d4");
        Piece rook = new Rook(Color.WHITE);
        Collection<Position> expected = Arrays.asList(
                Position.of("d1"), Position.of("d2"), Position.of("d3"), Position.of("d5"),
                Position.of("d6"), Position.of("d7"), Position.of("d8"),
                Position.of("a4"), Position.of("b4"), Position.of("c4"), Position.of("e4"),
                Position.of("f4"), Position.of("g4"), Position.of("h4")
        );

        //when
        Collection<Position> availableAttackPositions = rook.findAvailableAttackPositions(position);

        //then
        assertThat(availableAttackPositions)
                .containsAll(expected);
    }

    private static Stream<Arguments> createParameters() {
        return Stream.of(
                Arguments.of("d2", tuple(File.d, Rank.R3)),
                Arguments.of("d6", tuple(File.d, Rank.R5)),
                Arguments.of("b4", tuple(File.c, Rank.R4)),
                Arguments.of("f4", tuple(File.e, Rank.R4))
        );
    }
}
