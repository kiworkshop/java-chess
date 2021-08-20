package chess.service;

import chess.domain.piece.*;
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
    @DisplayName("진영별 점수를 계산한다")
    void testGetScore() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("같은 파일에 있는 폰의 갯수를 리턴한다")
    void testCalculatePawnScore() {
        //given
        ChessService service = new ChessService();
        ChessService service2 = new ChessService();
        service2.move("e2","e4");
        service2.move("e4","e5");
        service2.move("e5","e6");
        service2.move("f7","e6");

        //when
        double score = service.calculatePawnScore(Team.BLACK);
        double score2 = service2.calculatePawnScore(Team.BLACK);

        //then
        assertThat(score).isEqualTo(0);
        assertThat(score2).isEqualTo(1);
    }
}