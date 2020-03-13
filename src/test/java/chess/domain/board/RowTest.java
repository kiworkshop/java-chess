package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    @DisplayName("row의 값의 차이를 구할 수 있음")
    void differenceBetweenTwoRows() {
        Row rowOne = Row.ONE;
        Row rowEight = Row.EIGHT;
        assertThat(rowOne.differ(rowEight)).isEqualTo(7);
    }

    @Test
    @DisplayName("해당 row가 startingPosition인지 확인")
    void startingPosition() {
        Row rowOne = Row.ONE;
        Row rowThree = Row.THREE;
        assertThat(rowOne.isStartingPoint()).isTrue();
        assertThat(rowThree.isStartingPoint()).isFalse();
    }
}