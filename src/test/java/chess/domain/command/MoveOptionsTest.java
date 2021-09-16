package chess.domain.command;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.player.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoveOptionsTest {

    @Test
    @DisplayName("시작 위치를 반환한다.")
    void getSource() {
        //given
        List<String> options = Arrays.asList("a1", "b2");
        MoveOptions moveOptions = new MoveOptions(options);

        //when
        Position source = moveOptions.getSource();

        //then
        assertThat(source.getFile()).isEqualTo(File.A);
        assertThat(source.getRank()).isEqualTo(Rank.R1);
    }

    @Test
    @DisplayName("도착 위치를 반환한다.")
    void getTarget() {
        //given
        List<String> options = Arrays.asList("a1", "b2");
        MoveOptions moveOptions = new MoveOptions(options);

        //when
        Position target = moveOptions.getTarget();

        //then
        assertThat(target.getFile()).isEqualTo(File.B);
        assertThat(target.getRank()).isEqualTo(Rank.R2);
    }
}
