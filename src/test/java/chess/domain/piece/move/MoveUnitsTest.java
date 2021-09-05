package chess.domain.piece.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.move.MoveUnit.NORTH_EAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoveUnitsTest {

    @Test
    @DisplayName("매칭되는 이동 단위를 반환한다.")
    void find_match_move_unit() {
        // given
        MoveUnits moveUnits = MoveUnits.DIAGONAL;
        Gap gap = new Gap(2, 2);

        // when
        MoveUnit moveUnit = moveUnits.findMatchMoveUnit(gap, MoveLimit.INFINITE);

        // then
        assertThat(moveUnit).isSameAs(NORTH_EAST);
    }

    @Test
    @DisplayName("매칭되는 이동 단위가 없는 경우 예외를 던진다.")
    void find_match_move_unit_exception() {
        // given
        MoveUnits moveUnits = MoveUnits.KNIGHT;
        Gap gap = new Gap(2, 4);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> moveUnits.findMatchMoveUnit(gap, MoveLimit.FINITE))
                .withMessage("해당 방향으로 이동할 수 없습니다.");
    }
}