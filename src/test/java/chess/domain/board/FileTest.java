package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FileTest {

    @ParameterizedTest
    @CsvSource(value = {"1, A", "8, H"})
    @DisplayName("일치하는 파일 객체를 반환한다.")
    void of(int fileIndex, File expected) {
        //when
        File file = File.of(fileIndex);

        //then
        assertThat(file).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("일치하는 파일을 찾지 못할 경우 예외가 발생한다.")
    void of(int fileIndex) {
        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> File.of(fileIndex))
                .withMessage("일치하는 파일이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("다른 파일의 위치 값을 뺀 값을 반환한다.")
    void calculateGap() {
        //given
        int index1 = 8;
        int index2 = 1;
        File file1 = File.of(index1);
        File file2 = File.of(index2);

        //when
        int result = file1.calculateGap(file2);

        //then
        assertThat(result).isEqualTo(index1 - index2);
    }

    @Test
    @DisplayName("이동한 위치의 파일을 반환한다.")
    void move() {
        //given
        int index1 = 1;
        int index2 = 7;
        File file = File.of(index1);

        //when
        File movedFile = file.move(index2);

        //then
        assertThat(movedFile).isEqualTo(File.of(index1 + index2));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 7, true", "1, 8, false"})
    @DisplayName("이동하려는 위치가 이동 범위에 있는 지 확인한다.")
    void canMove(int fileIndex, int moveAmount, boolean expected) {
        //given
        File file = File.of(fileIndex);

        //when
        boolean actual = file.canMove(moveAmount);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
