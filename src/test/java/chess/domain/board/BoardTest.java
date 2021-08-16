package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {

    @ParameterizedTest
    @CsvSource({"a1, false", "a3, true"})
    @DisplayName("객체를 생성한다.")
    void create(String key, boolean expected) {
        //given
        Position position = Position.from(key);

        //when
        Board board = new Board();

        //then
        assertThat(board.isEmpty(position)).isEqualTo(expected);
    }
}
