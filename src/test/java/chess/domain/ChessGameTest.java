package chess.domain;

import chess.domain.command.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {

    @Test
    @DisplayName("게임 시작 명령어를 입력받을 경우 게임을 시작한다.")
    void run_start_command() {
        //given
        ChessGame chessGame = new ChessGame();
        Command command = new Command("start");

        //when
        chessGame.run(command);

        //then
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 명령어를 입력받을 경우 게임을 종료한다.")
    void run_end_command() {
        //given
        ChessGame chessGame = new ChessGame();
        Command command = new Command("end");

        //when
        chessGame.run(command);

        //then
        assertThat(chessGame.isRunning()).isFalse();
    }

    @Test
    @DisplayName("유효하지 않은 명령어를 입력받을 경우 예외를 던진다.")
    void run_invalid_command() {
        //given
        ChessGame chessGame = new ChessGame();
        Command command = new Command("invalid_command");

        //when, then
        assertThrows(UnsupportedOperationException.class, () -> chessGame.run(command));
    }
}
