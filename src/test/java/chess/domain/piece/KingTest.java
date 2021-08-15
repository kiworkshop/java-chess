package chess.domain.piece;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

  @Test
  @DisplayName("킹을 팀을 넣어서 생성하면 1팀 위치는 e8, 2팀은 e1에 , 표시명은 1팀K, 2팀k로 리턴한다")
  void testKingCreation() {

    //given
    King king1 = new King(Team.BLACK, new PiecePosition(File.E, Rank.ONE));
    King king2 = new King(Team.WHITE, new PiecePosition(File.E, Rank.EIGHT));

    //when // given
    assertThat(king1.getPiecePosition()).isEqualTo(new PiecePosition(File.E, Rank.ONE));
    assertThat(king1.getDisplayName()).isEqualTo("k");
    assertThat(king2.getPiecePosition()).isEqualTo(new PiecePosition(File.E, Rank.EIGHT));
    assertThat(king2.getDisplayName()).isEqualTo("K");
  }

}
