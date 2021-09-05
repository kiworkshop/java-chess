package chess.domain.board;

import chess.domain.command.MoveParameters;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Rook;
import chess.domain.player.Color;
import chess.domain.player.Scores;
import chess.exception.EmptyPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    @ParameterizedTest
    @CsvSource({"b3, b4, 자신의 기물만 움직일 수 있습니다.",  // 빈칸
            "a7, a6, 자신의 기물만 움직일 수 있습니다.",      // 적 기물
            "a1, a2, 자신의 기물이 있는 곳으로 이동할 수 없습니다.",
            "a1, a1, 출발 위치와 도착 위치가 같을 수 없습니다.",
            "a1, a3, 다른 기물을 통과하여 이동할 수 없습니다.",
            "c2, d3, 폰은 공격 대상이 있는 경우에만 대각선으로 이동할 수 있습니다."})
    @DisplayName("이동할 수 없는 경우 예외가 발생한다.")
    void move_exception(String source, String target, String exceptionMessage) {
        //given
        Board board = new Board();
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
        Board board = new Board();
        board.move(new MoveParameters("e2", "e4"));
        board.move(new MoveParameters("c7", "c5"));
        board.move(new MoveParameters("d2", "d4"));
        board.move(new MoveParameters("d8", "a5"));
        board.move(new MoveParameters("e1", "e2"));
        board.move(new MoveParameters("h7", "h5"));
        MoveParameters moveParameters = new MoveParameters(source, target);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(moveParameters))
                .withMessage("킹은 상대방이 공격 가능한 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("위치에 있는 기물을 반환한다.")
    void find_by() {
        //given
        Position whitePawnPosition = Position.of("d2");
        Position blackRookPosition = Position.of("a8");

        //when
        Board board = new Board();
        Piece whitePawn = board.findBy(whitePawnPosition);
        Piece blackRook = board.findBy(blackRookPosition);

        //then
        assertThat(whitePawn).isInstanceOf(Pawn.class);
        assertThat(whitePawn.isWhite()).isTrue();
        assertThat(blackRook).isInstanceOf(Rook.class);
        assertThat(blackRook.isWhite()).isFalse();
    }

    @Test
    @DisplayName("위치에 기물이 존재하지 않으면 예외를 던진다.")
    void find_by_empty() {
        //given
        Position emptyPosition = Position.of("d5");

        //when
        Board board = new Board();

        //then
        assertThrows(EmptyPositionException.class, () -> board.findBy(emptyPosition));
    }

    @Test
    @DisplayName("각 플레이어의 점수를 계산한다.")
    void get_scores() {
        //given
        Board board = new Board();
        board.move(new MoveParameters("a2", "a3"));
        board.move(new MoveParameters("e7", "e5"));
        board.move(new MoveParameters("d2", "d4"));
        board.move(new MoveParameters("e5", "e4"));
        board.move(new MoveParameters("b2", "b3"));
        board.move(new MoveParameters("a7", "a6"));
        board.move(new MoveParameters("f2", "f3"));
        board.move(new MoveParameters("e4", "f3"));

        //when
        Scores scores = board.getScores();

        //then
        assertThat(scores.getWhiteScore()).isEqualTo(37);
        assertThat(scores.getBlackScore()).isEqualTo(37);
    }

    @Test
    @DisplayName("흑팀 킹이 죽으면 백팀을 승자로 반환한다.")
    void get_winner_white() {
        //given
        Board board = new Board();
        board.move(new MoveParameters("e2", "e4"));
        board.move(new MoveParameters("f7", "f5"));
        board.move(new MoveParameters("d1", "h5"));
        board.move(new MoveParameters("a7", "a5"));
        board.move(new MoveParameters("h5", "e8"));

        //when
        Color winner = board.getWinner();

        //then
        assertThat(winner.isWhite()).isTrue();
    }

    @Test
    @DisplayName("백팀 킹이 죽으면 흑팀을 승자로 반환한다.")
    void get_winner_black() {
        //given
        Board board = new Board();
        board.move(new MoveParameters("f2", "f4"));
        board.move(new MoveParameters("e7", "e5"));
        board.move(new MoveParameters("a2", "a4"));
        board.move(new MoveParameters("d8", "h4"));
        board.move(new MoveParameters("b2", "b4"));
        board.move(new MoveParameters("h4", "e1"));

        //when
        Color winner = board.getWinner();

        //then
        assertThat(winner.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흑백 킹이 모두 살아있는 경우 승자를 요청하면 예외를 반환한다.")
    void get_winner_exception() {
        //given
        Board board = new Board();

        //when, then
        assertThatIllegalStateException()
                .isThrownBy(board::getWinner)
                .withMessage("King이 잡히지 않아 승자가 없습니다.");
    }
}
