package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.player.Position;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.groups.Tuple.tuple;

class QueenTest {

    @ParameterizedTest
    @MethodSource("createParametersForDiagonal")
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_diagonal(String targetPosition, Tuple expected) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Queen(Color.WHITE);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).extracting("file", "rank")
                .containsOnly(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d5", "c4", "e4", "d3"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_cardinal(String targetPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Queen(Color.WHITE);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f5", "e6", "b3", "c2"})
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target(String invalidTarget) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(invalidTarget);
        Piece piece = new Queen(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findPath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position position = Position.of("d4");
        Piece queen = new Queen(Color.WHITE);
        Collection<Position> expected = Arrays.asList(
                Position.of("a1"), Position.of("b2"), Position.of("c3"), Position.of("e5"), Position.of("f6"), Position.of("g7"), Position.of("h8"),
                Position.of("a7"), Position.of("b6"), Position.of("c5"), Position.of("e3"), Position.of("f2"), Position.of("g1"),
                Position.of("d1"), Position.of("d2"), Position.of("d3"), Position.of("d5"), Position.of("d6"), Position.of("d7"), Position.of("d8"),
                Position.of("a4"), Position.of("b4"), Position.of("c4"), Position.of("e4"), Position.of("f4"), Position.of("g4"), Position.of("h4")
        );

        //when
        Collection<Position> availableAttackPositions = queen.findAvailableAttackPositions(position);

        //then
        assertThat(availableAttackPositions)
                .hasSize(expected.size())
                .containsAll(expected);
    }

    private static Stream<Arguments> createParametersForDiagonal() {
        return Stream.of(
                Arguments.of("b6", tuple(File.c, Rank.R5)),
                Arguments.of("b2", tuple(File.c, Rank.R3)),
                Arguments.of("f2", tuple(File.e, Rank.R3)),
                Arguments.of("f6", tuple(File.e, Rank.R5))
        );
    }
}
