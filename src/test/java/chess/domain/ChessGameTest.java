package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {

    @Test
    @DisplayName("게임 시작 명령어를 입력받을 경우 게임을 시작한다.")
    void create_start_command() {
        //given
        String command = "start";

        //when
        ChessGame chessGame = new ChessGame(command);

        //then
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 명령어를 입력받을 경우 게임을 종료한다.")
    void create_end_command() {
        //given
        String command = "end";

        //when
        ChessGame chessGame = new ChessGame(command);

        //then
        assertThat(chessGame.isRunning()).isFalse();
    }

    @Test
    @DisplayName("유효하지 않은 명령어를 입력받을 경우 예외를 던진다.")
    void create_invalid_command() {
        //given
        String command = "invalid_command";

        //when, then
        assertThrows(UnsupportedOperationException.class, () -> new ChessGame(command));
    }
}
