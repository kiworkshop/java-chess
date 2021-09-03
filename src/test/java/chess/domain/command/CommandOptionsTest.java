package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandOptionsTest {

    @ParameterizedTest
    @CsvSource(value = {"start, 0", "end, 0", "move b2 b3, 2", "status, 0"})
    @DisplayName("옵션이 담긴 명령어를 반환한다.")
    void of(String text, int expected) {
        //when
        CommandOptions commandOptions = CommandOptions.of(text);

        //then
        assertThat(commandOptions.getOptions()).hasSize(expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("명령어가 null일 경우 예외가 발생한다.")
    void of_fail(String text) {
        //when //then
        assertThatThrownBy(() -> CommandOptions.of(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("명령어가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"status, move"})
    @DisplayName("시작 또는 종료 명령어가 아닐 경우 예외가 발생한다.")
    void validateInitialCommand(String text) {
        //given
        CommandOptions commandOptions = CommandOptions.of(text);

        //when // then
        assertThatThrownBy(commandOptions::validateInitialCommand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 또는 end를 입력해주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"start, false", "end, true", "move, false", "status, false"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isEnd(String text, boolean expected) {
        //given
        CommandOptions commandOptions = CommandOptions.of(text);

        //when
        boolean actual = commandOptions.isEnd();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"start, false", "end, false", "move, true", "status, false"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isMove(String text, boolean expected) {
        //given
        CommandOptions commandOptions = CommandOptions.of(text);

        //when
        boolean actual = commandOptions.isMove();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"start, false", "end, false", "move, false", "status, true"})
    @DisplayName("종료 명령어인지 확인한다.")
    void isStatus(String text, boolean expected) {
        //given
        CommandOptions commandOptions = CommandOptions.of(text);

        //when
        boolean actual = commandOptions.isStatus();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
