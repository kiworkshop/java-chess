package chess.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board board;

    @BeforeEach
    void before() {
        board = Board.of(BoardInitializer.reset());
    }

    @Test
    @DisplayName("보드판 생성을 테스트 한다.")
    void create() {
        //then
        assertThat(board).isInstanceOf(Board.class);
        assertThat(board.values()).hasSize(64);
    }
}
