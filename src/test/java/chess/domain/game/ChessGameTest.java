package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ChessGameTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void before() {
        chessGame = ChessGame.of(new Ready());
        board = chessGame.board();
    }

    @Test
    @DisplayName("체스 게임을 시작하면 상태가 Ready 에서 Playing 으로 변한다.")
    void start_game() {
        // when // then
        assertThat(chessGame.start().state()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("체스 게임을 준비하면 체스 판이 초기화 된다.")
    void setup_game_board() {
        //given //when //then
        assertThat(board.values()).hasSize(64);
    }

    @Test
    @DisplayName("체스 말을 움직이는 게임 상태는 Playing 이다.")
    void move() {
        //given
        ChessGame start = chessGame.start();
        Position sourcePosition = Position.from("b2");
        Position targetPosition = Position.from("b4");

        //when
        start.move(sourcePosition, targetPosition);

        //then
        assertThat(start.state()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("폰이 첫 수가 아닌 경우 2칸을 이동할 수 없다.")
    void not_first_move_positions() {
        //given
        ChessGame start = chessGame.start();
        start.move(Position.from("f2"), Position.from("f3"));
        start.move(Position.from("e7"), Position.from("e6"));
        Position whitePawn = Position.from("f3");
        Position blackPawn = Position.from("e6");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.move(whitePawn, Position.from("f5")))
                .withMessage("이동할 수 없는 위치입니다.");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.move(blackPawn, Position.from("e4")))
                .withMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("대각선 1칸 앞에 적의 기물이 있는 경우 기물을 뺏고 그 자리로 이동할 수 있다.")
    void attack_positions() {
        //given
        ChessGame start = chessGame.start();
        movePawnAttackPosition();

        //when //then
        start.move(Position.from("a4"), Position.from("b5"));
        start.move(Position.from("h5"), Position.from("g4"));
    }

    private void movePawnAttackPosition() {
        //white
        chessGame.move(Position.from("a2"), Position.from("a4"));
        //black
        chessGame.move(Position.from("b7"), Position.from("b5"));
        //white
        chessGame.move(Position.from("g2"), Position.from("g4"));
        //black
        chessGame.move(Position.from("h7"), Position.from("h5"));
    }

    @Test
    @DisplayName("체크메이트 되는 위치로는 이동할 수 없다.")
    void checkmate_positions() {
        //given
        ChessGame start = chessGame.start();
        moveCheckmatePosition();
        Position king = Position.from("e1");
        Position checkmatePosition = Position.from("e2");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(king, checkmatePosition))
                .withMessage("이동할 수 없는 위치입니다.");
    }

    private void moveCheckmatePosition() {
        //white
        chessGame.move(Position.from("e2"), Position.from("e4"));
        //black
        chessGame.move(Position.from("d7"), Position.from("d5"));
        //white
        chessGame.move(Position.from("g1"), Position.from("h3"));
        //black
        chessGame.move(Position.from("c8"), Position.from("g4"));
    }
}
