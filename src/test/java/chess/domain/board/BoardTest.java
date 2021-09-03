package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.player.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BoardTest {

    @Test
    @DisplayName("객체를 생성한다.")
    void create() {
        //given
        Position pawnPosition = Position.of("b2");
        Position emptyPosition = Position.of("b3");

        //when
        Board board = new Board();

        //then
        assertThat(board.isEmpty(pawnPosition)).isFalse();
        assertThat(board.isEmpty(emptyPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"b3, b4, 자신의 기물만 움직일 수 있습니다.",  // 빈칸
            "a7, a6, 자신의 기물만 움직일 수 있습니다.",      // 적 기물
            "a1, a2, 자신의 기물이 있는 곳으로 이동할 수 없습니다.",
            "a1, a1, 출발 위치와 도착 위치가 같을 수 없습니다.",
            "a1, a3, 다른 기물을 통과하여 이동할 수 없습니다."})
    @DisplayName("이동할 수 없는 경우 예외가 발생한다.")
    void move_exception(String sourcePosition, String targetPosition, String exceptionMessage) {
        //given
        Board board = new Board();
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters))
                .withMessage(exceptionMessage);
    }

    @ParameterizedTest
    @CsvSource({"e2, d2", "e2, e1"})
    @DisplayName("상대방이 킹의 목적지를 공격 가능한 경우 예외가 발생한다.")
    void move_king_invalid_target(String source, String target) {
        //given
        Board board = setBoardToAttackKing();
        MoveParameters moveParameters = new MoveParameters(Position.of(source), Position.of(target));

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters))
                .withMessage("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("모든 플레이어의 점수를 반환한다.")
    void get_status() {
        //given
        Board board = setBoardToGetStatus();

        //when
        Scores scores = board.getScores();

        //then
        assertThat(scores.getWhiteScore()).isEqualTo(36);
        assertThat(scores.getBlackScore()).isEqualTo(37);
    }

    private Board setBoardToGetStatus() {
        Board board = new Board();
        board.move(new MoveParameters(Position.of("a2"), Position.of("a3")));
        board.move(new MoveParameters(Position.of("e7"), Position.of("e5")));
        board.move(new MoveParameters(Position.of("d2"), Position.of("e3")));
        board.move(new MoveParameters(Position.of("e5"), Position.of("e4")));
        board.move(new MoveParameters(Position.of("b2"), Position.of("b3")));
        board.move(new MoveParameters(Position.of("a7"), Position.of("a6")));
        board.move(new MoveParameters(Position.of("f2"), Position.of("f3")));
        board.move(new MoveParameters(Position.of("e4"), Position.of("f3")));
        return board;
    }

    private Board setBoardToAttackKing() {
        Board board = new Board();
        board.move(new MoveParameters(Position.of("e2"), Position.of("e4")));
        board.move(new MoveParameters(Position.of("c7"), Position.of("c5")));
        board.move(new MoveParameters(Position.of("d2"), Position.of("d4")));
        board.move(new MoveParameters(Position.of("d8"), Position.of("a5")));
        board.move(new MoveParameters(Position.of("e1"), Position.of("e2")));
        board.move(new MoveParameters(Position.of("h7"), Position.of("h5")));
        return board;
    }
}
