package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource({"WHITE, a1, a8", "BLACK, a8, a1"})
    @DisplayName("색상에 따라 플레이어 객체를 생성한다.")
    void create_with_color(Color color, String presentPosition, String notPresentPosition) {
        //given
        Position present = Position.of(presentPosition);
        Position notPresent = Position.of(notPresentPosition);

        // when
        Player player = new Player(color);

        //then
        assertThat(player.hasPieceOn(present)).isTrue();
        assertThat(player.hasNoPieceOn(notPresent)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"WHITE, b2, b3", "BLACK, d7, d6"})
    @DisplayName("기물을 움직인다.")
    void update_board(Color color, String sourcePosition, String targetPosition) {
        //given
        Player player = new Player(color);
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);

        //when
        player.move(source, target);

        //then
        assertThat(player.hasNoPieceOn(source)).isTrue();
        assertThat(player.hasPieceOn(target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"WHITE, b3, b4", "BLACK, b6, b5"})
    @DisplayName("이동시킬 기물이 존재하지 않을 경우 예외가 발생한다.")
    void update_source_position_empty(Color color, String sourcePosition, String targetPosition) {
        //given
        Player player = new Player(color);
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> player.move(source, target))
                .withMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource({"WHITE, b2, b4, b3", "BLACK, d7, d5, d6"})
    @DisplayName("기물 이동 경로를 반환한다.")
    void find_paths(Color color, String sourcePosition, String targetPosition, String expectedPosition) {
        //given
        Player player = new Player(color);
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Path expected = new Path(Position.of(expectedPosition));

        //when
        Path path = player.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"WHITE, c3, b2, d2, b1", "BLACK, c6, b7, d7, b8"})
    @DisplayName("공격 가능한 모든 경로를 반환한다.")
    void find_attack_paths(Color color, String targetPosition, String leftPath, String rightPath, String blocked) {
        // given
        Player player = new Player(color);
        Position target = Position.of(targetPosition);
        Collection<Path> expected = Arrays.asList(
                new Path(Position.of(leftPath)),
                new Path(Position.of(rightPath))
        );
        player.wasAttackedBy(Position.of(blocked));

        // when
        Collection<Path> attackPaths = player.findAttackPaths(target);

        // then
        assertThat(attackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }

    @ParameterizedTest
    @CsvSource({"WHITE, e2", "BLACK, e7"})
    @DisplayName("적에게 공격받은 경우 기물을 제거한다.")
    void was_attacked_by(Color color, String targetPosition) {
        // given
        Player player = new Player(color);
        Position target = Position.of(targetPosition);

        // when
        player.wasAttackedBy(target);

        // then
        assertThat(player.hasNoPieceOn(target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"WHITE, e1, e2", "BLACK, e8, e7"})
    @DisplayName("주어진 위치에 킹이 있는지 확인한다.")
    void has_king_on(Color color, String kingPosition, String notKingPosition) {
        // given
        Player player = new Player(color);

        // when
        boolean isKing = player.hasKingOn(Position.of(kingPosition));
        boolean isNotKing = player.hasKingOn(Position.of(notKingPosition));

        // then
        assertThat(isKing).isTrue();
        assertThat(isNotKing).isFalse();
    }

    @Test
    @DisplayName("킹이 존재하는지 반환한다.")
    void is_king_dead() {
        //given
        Player player = new Player(Color.WHITE);
        player.wasAttackedBy(Position.of("e1"));

        //when
        boolean kingDead = player.isKingDead();

        //then
        assertThat(kingDead).isTrue();
    }

    @Test
    @DisplayName("현재 남아있는 피스의 점수 합을 구한다.")
    void sum_scores() {
        //given
        Player player = new Player(Color.WHITE);

        //when
        double sum = player.calculateScores();

        //then
        assertThat(sum).isEqualTo(38);
    }
}
