package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.Finish;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ChessGameTest {
    private ChessGame ready;

    @BeforeEach
    void before() {
        ready = ChessGame.of(new Ready());
    }

    @Test
    @DisplayName("체스 게임을 시작하면 상태가 Ready 에서 Playing 으로 변한다.")
    void start_game() {
        //given //when
        ChessGame start = ready.start();

        //then
        assertThat(start.gameState()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("체스 게임을 준비하면 체스 판이 초기화 된다.")
    void setup_game_board() {
        //given //when
        Board board = ready.board();

        // then
        assertThat(board.values()).hasSize(64);
    }

    @Test
    @DisplayName("체스 말을 움직이는 게임 상태는 Playing 이다.")
    void move() {
        //given
        ChessGame start = ready.start();
        Position sourcePosition = Position.from("b2");
        Position targetPosition = Position.from("b4");

        //when
        ChessGame playing = start.moveAndToggleTurn(sourcePosition, targetPosition);

        //then
        assertThat(playing.gameState()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("폰이 첫 수가 아닌 경우 2칸을 이동할 수 없다.")
    void not_first_move_positions() {
        //given
        ChessGame start = ready.start();
        start.moveAndToggleTurn(Position.from("f2"), Position.from("f3"));
        start.moveAndToggleTurn(Position.from("e7"), Position.from("e6"));
        Position whitePawn = Position.from("f3");
        Position blackPawn = Position.from("e6");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.moveAndToggleTurn(whitePawn, Position.from("f5")))
                .withMessage("이동할 수 없는 위치입니다.");

        start.moveAndToggleTurn(whitePawn, Position.from("f4")); // 턴을 바꾸려고 추가

        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.moveAndToggleTurn(blackPawn, Position.from("e4")))
                .withMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("대각선 1칸 앞에 적의 기물이 있는 경우 기물을 뺏고 그 자리로 이동할 수 있다.")
    void attack_positions() {
        //given
        ChessGame start = movePawnAttackPosition(ready.start());

        //when //then
        start.moveAndToggleTurn(Position.from("a4"), Position.from("b5"));
        start.moveAndToggleTurn(Position.from("h5"), Position.from("g4"));
    }

    private ChessGame movePawnAttackPosition(ChessGame start) {
        //white
        start.moveAndToggleTurn(Position.from("a2"), Position.from("a4"));
        //black
        start.moveAndToggleTurn(Position.from("b7"), Position.from("b5"));
        //white
        start.moveAndToggleTurn(Position.from("g2"), Position.from("g4"));
        //black
        start.moveAndToggleTurn(Position.from("h7"), Position.from("h5"));
        return start;
    }

    @Test
    @DisplayName("체크메이트 되는 위치로는 이동할 수 없다.")
    void checkmate_positions() {
        //given
        ChessGame start = moveCheckmatePosition(ready.start());
        Position king = Position.from("e1");
        Position checkmatePosition = Position.from("e2");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.moveAndToggleTurn(king, checkmatePosition))
                .withMessage("이동할 수 없는 위치입니다.");
    }

    private ChessGame moveCheckmatePosition(ChessGame start) {
        //white
        start.moveAndToggleTurn(Position.from("e2"), Position.from("e4"));
        //black
        start.moveAndToggleTurn(Position.from("d7"), Position.from("d5"));
        //white
        start.moveAndToggleTurn(Position.from("g1"), Position.from("h3"));
        //black
        start.moveAndToggleTurn(Position.from("c8"), Position.from("g4"));
        return start;
    }

    @Test
    @DisplayName("체스 말을 움직일 차례가 아닌 경우 예외가 발생한다.")
    void can_not_move_turn() {
        //given
        ChessGame start = ready.start();

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.moveAndToggleTurn(Position.from("g7"), Position.from("g6")))
                .withMessage("백이 움직일 차례입니다.");
    }

    @Test
    @DisplayName("킹이 잡히면 게임이 종료된다.")
    void checkmate() {
        //given
        ChessGame start = moveCheckmate(ready.start());

        //when
        ChessGame end = start.moveAndToggleTurn(Position.from("g4"), Position.from("e2"));

        // then
        assertThat(end.gameState()).isInstanceOf(Finish.class);
    }

    private ChessGame moveCheckmate(ChessGame start) {
        //white
        start.moveAndToggleTurn(Position.from("e2"), Position.from("e4"));
        //black
        start.moveAndToggleTurn(Position.from("d7"), Position.from("d5"));
        //white
        start.moveAndToggleTurn(Position.from("e1"), Position.from("e2"));
        //black
        start.moveAndToggleTurn(Position.from("c8"), Position.from("g4"));
        //white
        start.moveAndToggleTurn(Position.from("g1"), Position.from("h3"));
        return start;
    }
}
