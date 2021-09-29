package chess.domain.team;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class TeamTest {

    @Test
    @DisplayName("WHITE 팀 객체를 생성한다.")
    void create_with_color() {
        //given
        Position present = Position.of("a1");
        Position notPresent = Position.of("a8");

        // when
        Team team = Team.white();

        //then
        assertThat(team.hasPieceOn(present)).isTrue();
        assertThat(team.hasNoPieceOn(notPresent)).isTrue();
    }

    @Test
    @DisplayName("BLACK 팀 객체를 생성한다.")
    void create_black_team() {
        //given
        Position present = Position.of("a8");
        Position notPresent = Position.of("a1");

        // when
        Team team = Team.black();

        //then
        assertThat(team.hasPieceOn(present)).isTrue();
        assertThat(team.hasNoPieceOn(notPresent)).isTrue();
    }

    @Test
    @DisplayName("기물을 움직인다.")
    void update_board() {
        //given
        Team team = Team.white();
        Position source = Position.of("b2");
        Position target = Position.of("b3");

        //when
        team.movePiece(source, target);

        //then
        assertThat(team.hasNoPieceOn(source)).isTrue();
        assertThat(team.hasPieceOn(target)).isTrue();
    }

    @Test
    @DisplayName("이동시킬 기물이 존재하지 않을 경우 예외가 발생한다.")
    void update_source_position_empty() {
        //given
        Team team = Team.white();
        Position source = Position.of("b3");
        Position target = Position.of("b4");

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> team.movePiece(source, target))
                .withMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("기물 이동 경로를 반환한다.")
    void find_paths() {
        //given
        Team team = Team.white();
        Position source = Position.of("b2");
        Position target = Position.of("b4");
        Path expected = new Path(Position.of("b3"));

        //when
        Path path = team.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("공격 가능한 모든 경로를 반환한다.")
    void find_attack_paths() {
        // given
        Team team = Team.white();
        Position target = Position.of("c3");
        Collection<Path> expected = Arrays.asList(
                new Path(Position.of("b2")),
                new Path(Position.of("d2"))
        );
        team.removePiece(Position.of("b1"));

        // when
        Collection<Path> attackPaths = team.findAttackPaths(target);

        // then
        assertThat(attackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }

    @Test
    @DisplayName("적에게 공격받은 경우 기물을 제거한다.")
    void was_attacked_by() {
        // given
        Team team = Team.white();
        Position target = Position.of("e2");

        // when
        team.removePiece(target);

        // then
        assertThat(team.hasNoPieceOn(target)).isTrue();
    }

    @Test
    @DisplayName("주어진 위치에 킹이 있는지 확인한다.")
    void has_king_on() {
        // given
        Team team = Team.white();

        // when
        boolean isKing = team.hasKingOn(Position.of("e1"));
        boolean isNotKing = team.hasKingOn(Position.of("e2"));

        // then
        assertThat(isKing).isTrue();
        assertThat(isNotKing).isFalse();
    }

    @Test
    @DisplayName("킹이 존재하는지 반환한다.")
    void is_king_dead() {
        //given
        Team team = Team.white();
        team.removePiece(Position.of("e1"));

        //when
        boolean kingDead = team.isKingDead();

        //then
        assertThat(kingDead).isTrue();
    }

    @Test
    @DisplayName("현재 남아있는 피스의 점수 합을 구한다.")
    void sum_scores() {
        //given
        Team team = Team.white();

        //when
        double sum = team.calculateScores();

        //then
        assertThat(sum).isEqualTo(38);
    }
}
