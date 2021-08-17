package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        Set<Position> paths = piece.findPaths(source, target);

        //then
        assertThat(paths).extracting("file", "rank")
                .containsOnly(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d5", "c4", "e4", "d3"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_straight(String targetPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Queen(Color.WHITE);

        //when
        Set<Position> paths = piece.findPaths(source, target);

        //then
        assertThat(paths).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "d2", "b4", "f4", "f5"})
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target(String invalidTarget) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(invalidTarget);
        Piece piece = new Queen(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPaths(source, target));
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
