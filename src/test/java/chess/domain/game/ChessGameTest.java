package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import chess.domain.state.Finish;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void before() {
        chessGame = ChessGame.of(new Ready());
    }

    @Test
    @DisplayName("체스 게임을 시작하면 상태가 Ready 에서 Playing 으로 변한다.")
    void start_game() {
        //given //when
        ChessGame start = chessGame.start();

        //then
        assertThat(start.gameState()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("체스 게임을 준비하면 체스 판이 초기화 된다.")
    void setup_game_board() {
        //given //when
        Board board = chessGame.getBoard();

        // then
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
        ChessGame playing = start.moveAndToggleTurn(sourcePosition, targetPosition);

        //then
        assertThat(playing.gameState()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("대각선 1칸 앞에 적의 기물이 있는 경우 기물을 뺏고 그 자리로 이동할 수 있다.")
    void attack_positions() {
        //given
        ChessGame start = movePawnAttackPosition(chessGame.start());
        Board board = start.getBoard();

        //when
        start.moveAndToggleTurn(Position.from("a4"), Position.from("b5"));
        start.moveAndToggleTurn(Position.from("h5"), Position.from("g4"));

        //then
        assertThat(board.from(Position.from("b5"))).isInstanceOf(Pawn.class);
        assertThat(board.from(Position.from("g4"))).isInstanceOf(Pawn.class);
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
    @DisplayName("체스 말을 움직일 차례가 아닌 경우 예외가 발생한다.")
    void can_not_move_turn() {
        //given
        ChessGame start = chessGame.start();

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> start.moveAndToggleTurn(Position.from("g7"), Position.from("g6")))
                .withMessage("백이 움직일 차례입니다.");
    }

    @Test
    @DisplayName("킹이 잡히면 게임이 종료된다.")
    void checkmate() {
        //given
        ChessGame start = moveCheckmate(chessGame.start());

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
