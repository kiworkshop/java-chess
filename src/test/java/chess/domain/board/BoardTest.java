package chess.domain.board;

import chess.domain.command.MoveParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {

    @ParameterizedTest
    @CsvSource({"a1, false", "a3, true"})
    @DisplayName("객체를 생성한다.")
    void create(String key, boolean expected) {
        //given
        Position position = Position.of(key);

        //when
        Board board = new Board();

        //then
        assertThat(board.isEmpty(position)).isEqualTo(expected);
    }

    @Test
    @DisplayName("인자로 받은 시작 위치에 기물이 존재하지 않을 경우 예외가 발생한다.")
    void move_invalid_source_position() {
        //given
        Board board = new Board();
        Position source = Position.of("b3");
        Position target = Position.of("b4");
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> board.move(moveParameters, true));
    }

    @Test
    @DisplayName("인자로 받은 색상과 일치하지 않을 경우 예외가 발생한다.")
    void move_different_color() {
        //given
        Board board = new Board();
        Position source = Position.of("b2");
        Position target = Position.of("b3");
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> board.move(moveParameters, false));
    }

    @Test
    @DisplayName("인자로 받은 시작과 도착 위치의 기물이 같은 색상일 경우 예외가 발생한다.")
    void move_source_and_destination_same_color() {
        //given
        Board board = new Board();
        Position source = Position.of("a1");
        Position target = Position.of("a2");
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> board.move(moveParameters, true));
    }
}
