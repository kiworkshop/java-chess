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
}
