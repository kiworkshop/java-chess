package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start, START", "end, END", "move, MOVE", "status, STATUS"})
    @DisplayName("명령어를 반환한다.")
    void of(String text, Command expectedCommand) {
        //when
        Command command = Command.of(text);

        //then
        assertThat(command).isEqualTo(expectedCommand);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"star", "reset", "undo", "redo", "show"})
    @DisplayName("명령어를 찾을 수 없을 경우 예외가 발생한다.")
    void of_fail(String text) {
        //when //then
        assertThatThrownBy(() -> Command.of(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 명령어입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"STATUS, MOVE"})
    @DisplayName("시작 또는 종료 명령어가 아닐 경우 예외가 발생한다.")
    void validateInitialCommand(Command command) {
        //when // then
        assertThatThrownBy(command::validateInitialCommand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 또는 end를 입력해주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"START, false", "END, true", "MOVE, false", "STATUS, false"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isEnd(Command command, boolean expected) {
        //when
        boolean actual = command.isEnd();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"START, false", "END, false", "MOVE, true", "STATUS, false"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isMove(Command command, boolean expected) {
        //when
        boolean actual = command.isMove();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"START, false", "END, false", "MOVE, false", "STATUS, true"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isStatus(Command command, boolean expected) {
        //when
        boolean actual = command.isStatus();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
