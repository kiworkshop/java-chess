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
        ChessService service = new ChessService();
        service.start();
        service.move("e2","e4");
        service.move("e4","e5");
        service.move("e5","e6");
        service.move("f7","e6");

        //when

        //then
        assertThat(service.getGameScore(Team.BLACK)).isEqualTo(37);
        assertThat(service.getGameScore(Team.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("같은 파일에 있는 폰의 갯수를 리턴한다")
    void testCalculatePawnScore() {
        //given
        ChessService service = new ChessService();
        service.start();
        ChessService service2 = new ChessService();
        service2.start();
        service2.move("e2","e4");
        service2.move("e4","e5");
        service2.move("e5","e6");
        service2.move("f7","e6");

        //when
        double score = service.calculatePawnScore(Team.BLACK);
        double score2 = service2.calculatePawnScore(Team.BLACK);

        //then
        assertThat(score).isZero();
        assertThat(score2).isEqualTo(1);
    }

    @Test
    @DisplayName("Black King이 잡혔는지 확인하는 메서드 테스트")
    void testDetectBlackKingIsDead() {
        //given
        ChessService service = new ChessService();
        service.start();
        service.getChessPlate().getPlate().remove(new PiecePosition(File.E, Rank.EIGHT));

        //when

        //then
        assertThat(service.isKingDead(Team.BLACK)).isTrue();
        assertThat(service.isKingDead(Team.WHITE)).isFalse();
    }

    @Test
    @DisplayName("White King이 잡혔는지 확인하는 메서드 테스트")
    void testDetectWhiteKingIsDead() {
        //given
        ChessService service = new ChessService();
        service.start();
        service.getChessPlate().getPlate().remove(new PiecePosition(File.E, Rank.ONE));

        //when

        //then
        assertThat(service.isKingDead(Team.BLACK)).isFalse();
        assertThat(service.isKingDead(Team.WHITE)).isTrue();
    }
}