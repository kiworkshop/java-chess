package chess.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @Test
    @DisplayName("백에서 흑으로 색을 반전한다.")
    void next_turn_white() {
        // given
        Color color = Color.WHITE;

        // when
        Color next = color.flip();

        // then
        assertThat(next.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흑에서 백으로 색을 반전한다.")
    void next_turn_black() {
        // given
        Color color = Color.BLACK;

        // when
        Color next = color.flip();

        // then
        assertThat(next.isWhite()).isTrue();
    }
}