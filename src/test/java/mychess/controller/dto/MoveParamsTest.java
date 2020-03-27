package mychess.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveParamsTest {

    @Test
    @DisplayName("정상")
    void validParameters() {
        assertThatCode(() -> MoveParams.of(Arrays.asList("A2", "B7")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("장외 포지션이 존재: x축")
    void invalidParameterOverflowedX() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("I1", "B1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("장외 포지션이 존재: y축")
    void invalidParameterOverflowedY() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1", "B9")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("파라미터가 출발점 도착점 2개가 아님")
    void parameterSizeValidation() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1", "B7", "A3")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("출발점과 도착점이 같다")
    void sourceDestinationAreSame() {
        assertThatThrownBy(() -> MoveParams.of(Arrays.asList("A1", "A1")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}