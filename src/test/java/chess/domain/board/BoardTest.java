package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.Pawn;
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
        assertThat(board.findBy(pawnPosition)).isInstanceOf(Pawn.class);
        assertThat(board.isEmpty(emptyPosition)).isTrue();
    }

    @Test
    @DisplayName("인자로 받은 시작 위치에 기물이 존재하지 않을 경우 예외가 발생한다.")
    void move_source_position_empty() {
        //given
        Board board = new Board();
        Position source = Position.of("b3");
        Position target = Position.of("b4");
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, true))
                .withMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource({"b2, b3, false", "a7, a6, true"})
    @DisplayName("자신의 기물이 아닌 기물을 선택할 경우 예외가 발생한다.")
    void move_source_not_owner(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        //given
        Board board = new Board();
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, isWhiteTurn))
                .withMessage("자신의 기물만 움직일 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource({"a1, a2, true", "a8, a7, false"})
    @DisplayName("시작과 도착 위치의 기물이 같은 색상일 경우 예외가 발생한다.")
    void move_source_and_target_same_color(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        //given
        Board board = new Board();
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, isWhiteTurn))
                .withMessage("같은 색상의 기물은 공격할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"a1, a1, true", "a8, a8, false"})
    @DisplayName("시작과 도착 위치가 같을 경우 예외가 발생한다.")
    void move_source_and_target_same(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        //given
        Board board = new Board();
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, isWhiteTurn))
                .withMessage("출발 위치와 도착 위치가 같을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"a1, a3, true", "a8, a6, false"})
    @DisplayName("경로에 다른 기물이 존재하는 경우 예외가 발생한다.")
    void move_invalid_paths(String sourcePosition, String targetPosition, boolean isWhiteTurn) {
        //given
        Board board = new Board();
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, isWhiteTurn))
                .withMessage("기물을 통과하여 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"e2, d2", "e2, e1"})
    @DisplayName("킹 도착지를 상대방이 공격 가능한 경우 예외가 발생한다.")
    void move_king_invalid_target(String source, String target) {
        //given
        Board board = setBoardToAttackKing();
        MoveParameters moveParameters = new MoveParameters(Position.of(source), Position.of(target));

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters, true))
                .withMessage("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("모든 플레이어의 점수를 반환한다.")
    void get_status() {
        //given
        Board board = setBoardToGetStatus();

        //when
        Status status = board.getStatus();

        //then
        assertThat(status.getWhiteScore()).isEqualTo(37);
        assertThat(status.getBlackScore()).isEqualTo(37);
    }

    private Board setBoardToGetStatus() {
        Board board = new Board();
        board.move(new MoveParameters(Position.of("e7"), Position.of("e5")), false);
        board.move(new MoveParameters(Position.of("e5"), Position.of("e4")), false);
        board.move(new MoveParameters(Position.of("e4"), Position.of("e3")), false);
        board.move(new MoveParameters(Position.of("d2"), Position.of("e3")), true);
        return board;
    }

    private Board setBoardToAttackKing() {
        Board board = new Board();
        board.move(new MoveParameters(Position.of("e2"), Position.of("e4")), true);
        board.move(new MoveParameters(Position.of("d2"), Position.of("d4")), true);
        board.move(new MoveParameters(Position.of("e1"), Position.of("e2")), true);
        board.move(new MoveParameters(Position.of("c7"), Position.of("c5")), false);
        board.move(new MoveParameters(Position.of("d8"), Position.of("a5")), false);
        return board;
    }
}
