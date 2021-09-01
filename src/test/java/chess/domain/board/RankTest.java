package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {"1, R1", "8, R8"})
    @DisplayName("일치하는 랭크 객체를 반환한다.")
    void of(int rankIndex, Rank expected) {
        //when
        Rank rank = Rank.of(rankIndex);

        //then
        assertThat(rank).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("일치하는 랭크를 찾지 못할 경우 예외가 발생한다.")
    void of(int rankIndex) {
        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Rank.of(rankIndex))
                .withMessage("일치하는 랭크가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("다른 랭크의 위치 값을 뺀 값을 반환한다.")
    void calculateGap() {
        //given
        int index1 = 8;
        int index2 = 1;
        Rank rank1 = Rank.of(index1);
        Rank rank2 = Rank.of(index2);

        //when
        int result = rank1.calculateGap(rank2);

        //then
        assertThat(result).isEqualTo(index1 - index2);
    }

    @Test
    @DisplayName("이동한 위치의 랭크를 반환한다.")
    void move() {
        //given
        int index1 = 1;
        int index2 = 7;
        Rank rank = Rank.of(index1);

        //when
        Rank movedRank = rank.move(index2);

        //then
        assertThat(movedRank).isEqualTo(Rank.of(index1 + index2));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 7, true", "1, 8, false"})
    @DisplayName("이동하려는 위치가 이동 범위에 있는 지 확인한다.")
    void canMove(int rankIndex, int moveAmount, boolean expected) {
        //given
        Rank rank = Rank.of(rankIndex);

        //when
        boolean actual = rank.canMove(moveAmount);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
