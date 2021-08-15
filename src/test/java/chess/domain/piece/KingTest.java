package chess.domain.piece;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

  @Test
  @DisplayName("킹을 팀을 넣어서 생성하면 1팀 위치는 e8, 2팀은 e1에 , 표시명은 1팀K, 2팀k로 리턴한다")
  void create() {

    //given
    King king1 = new King("1");
    King king2 = new King("2");

    //when // given
    assertThat(king1.getPosition()).isEqual("E8");
    assertThat(king1.getDisplayName()).isEqual("K");
    assertThat(king2.getPosition()).isEqual("E1");
    assertThat(king2.getDisplayName()).isEqual("k");
  }

}
