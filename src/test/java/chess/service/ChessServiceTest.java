package chess.service;

import chess.domain.piece.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChessServiceTest {
    @Test
    @DisplayName("File 생성 테스트")
    void generateFileWithString() {
        //given
        File a = File.valueOfString("b");

        //when

        //then
        assertThat(a.getFileMarker()).isEqualTo("b");
        assertThat(a.getFilePosition()).isEqualTo(2);
    }
}