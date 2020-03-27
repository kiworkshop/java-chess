package chess.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveParamsTest {

    @Test
    @DisplayName("정상 상황")
    void validParameters() {
        assertThatCode(() -> MoveParams.of(Arrays.asList("A1", "A5")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("도착지의 position이 잘못 입력된 경우")
    void invalidPositionOnDestination() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1", "I8")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("출발지의 position이 잘못 입력된 경우")
    void invalidPositionOnSource() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("I8", "A1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 값이 3개이면 예외 발생")
    void invalidParameter() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1", "A2", "A3")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1")))
                .isInstanceOf(IllegalArgumentException.class);

    }
}