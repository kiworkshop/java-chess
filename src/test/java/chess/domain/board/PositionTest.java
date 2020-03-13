package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    @DisplayName("각 포지션은 cache되어 관리")
    void position() {
        assertThat(Position.from("A1")).isEqualTo(Position.from("A1"));
    }

    @Test
    @DisplayName("존재하지 않는 포지션의 값을 가져오려고 하면 예외 발생")
    void positionException() {
        assertThatThrownBy(() -> Position.from("A9"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 포지션의 column차이를 구하기")
    void columnDistanceBetweenTwoPositions() {
        Position positionA1 = Position.from("A1");
        Position positionH1 = Position.from("H1");
        assertThat(positionA1.colDistance(positionH1)).isEqualTo(7);
    }

    @Test
    @DisplayName("두 포지션의 row차이를 구하기")
    void rowDistanceBetweenTwoPosition() {
        Position positionA1 = Position.from("A1");
        Position positionA8 = Position.from("A8");
        assertThat(positionA1.rowDistance(positionA8)).isEqualTo(7);
    }

    @Test
    @DisplayName("해당 포지션이 starting position인지 확인")
    void checkStartingPosition() {
        Position positionA1 = Position.from("A1");
        Position positionA3 = Position.from("A3");
        assertThat(positionA1.isStartingPosition()).isTrue();
        assertThat(positionA3.isStartingPosition()).isFalse();
    }

    @Test
    @DisplayName("해당 포지션이 white의 starting position인지 확인")
    void checkWhiteStartingPosition() {
        Position positionA8 = Position.from("A8");
        Position positionA6 = Position.from("A6");
        assertThat(positionA8.isWhiteStartingPosition()).isTrue();
        assertThat(positionA6.isWhiteStartingPosition()).isFalse();
    }
}