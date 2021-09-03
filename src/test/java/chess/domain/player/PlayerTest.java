package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource({"b2, true", "b3, false"})
    @DisplayName("피스 색상을 넣어서 플레이어 객체를 생성한다.")
    void create_with_color(String key, boolean expected) {
        //given
        Position position = Position.of(key);
        Color color = Color.WHITE;

        // when
        Player player = new Player(color);

        //then
        assertThat(player.hasPieceOn(position)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"WHITE, b3, b4", "BLACK, b6, b5"})
    @DisplayName("시작 위치에 기물이 존재하지 않을 경우 예외가 발생한다.")
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
        assertThat(player.hasPieceOn(source)).isFalse();
        assertThat(player.hasPieceOn(target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"WHITE, b2, b4, b3", "BLACK, d7, d5, d6"})
    @DisplayName("이동 경로를 반환한다.")
    void find_paths(Color color, String sourcePosition, String targetPosition, String expected) {
        //given
        Player player = new Player(color);
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Position path = Position.of(expected);

        //when
        Set<Position> paths = player.findPaths(source, target);

        //then
        assertThat(paths).containsOnly(path);
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

    @ParameterizedTest
    @CsvSource({"WHITE, b3, e6", "BLACK, e6, b3"})
    @DisplayName("주어진 위치를 공격할 수 있는지 확인한다.")
    void can_attack(Color color, String attackPosition, String notAttackPosition) {
        // given
        Player player = new Player(color);

        // when
        boolean can = player.canAttack(Position.of(attackPosition));
        boolean cannot = player.canAttack(Position.of(notAttackPosition));

        // then
        assertThat(can).isTrue();
        assertThat(cannot).isFalse();
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

    @Test
    @DisplayName("킹이 존재하는지 반환한다.")
    void is_king_dead() {
        //given
        Player player = new Player(Color.WHITE);
        player.isUnderAttack(Position.of("e1"));

        //when
        boolean kingDead = player.isKingDead();

        //then
        assertThat(kingDead).isTrue();
    }
}
