package chess.service;

import chess.domain.piece.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessServiceTest {
    @Test
    @DisplayName("File 생성 테스트")
    void generateFileWithString() {
        //given
        File a = File.findBy("b");

        //when

        //then
        assertThat(a.getFileMarker()).isEqualTo("b");
        assertThat(a.getFilePosition()).isEqualTo(2);
    }

    @Test
    @DisplayName("진영별 점수를 계산한다.")
    void () {
        //given

        //when

        //then
    }
}