package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {

    @Test
    @DisplayName("두 컬럼의 값 차를 구함")
    void differenceBetweenTwoColumn() {
        Column columnA = Column.A;
        Column columnH = Column.H;
        assertThat(columnA.differ(columnH)).isEqualTo(7);
    }
}