package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("보드판 생성을 테스트 한다.")
    void createTest() {
        //given

        //when
        Board board = Board.of(BoardInitializer.reset());

        //then
        assertThat(board).isInstanceOf(Board.class);
    }

}
