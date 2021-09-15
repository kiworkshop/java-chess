package chess.domain.piece.move;

import chess.domain.board.Position;
import chess.domain.team.Color;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @Test
    @DisplayName("주어진 경로에서 타겟 이전까지의 경로만 반환한다.")
    void get_positions_until_target() {
        // given
        Path path = new Path(Position.of("d1"), Position.of("d2"), Position.of("d3"), Position.of("d4"));
        Position target = Position.of("d3");
        Path expected = new Path(Position.of("d1"), Position.of("d2"));

        // when
        Path positionsUntilTarget = path.getPositionsUntilTarget(target);

        // then
        assertThat(positionsUntilTarget).isEqualTo(expected);
    }

    @Test
    @DisplayName("경로가 기물로 막혀있는지 확인한다.")
    void is_blocked_by() {
        // given
        Path path = new Path(Position.of("c4"), Position.of("c3"), Position.of("c2"));
        Team team = new Team(Color.WHITE);

        // when
        boolean blockedBy = path.isBlockedBy(team);
        boolean notBlockedBy = path.isNotBlockedBy(team);

        // then
        assertThat(blockedBy).isTrue();
        assertThat(notBlockedBy).isFalse();
    }
}