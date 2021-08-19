package chess.domain;

import chess.domain.position.Position;
import chess.domain.state.Finish;
import chess.domain.state.Playing;
import chess.domain.state.Ready;
import chess.dto.ChessGameDto;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {
    private ChessGame chessGame;
    private ChessGameDto chessGameDto;

    @BeforeEach
    void before() {
        chessGame = new ChessGame();
        chessGameDto = chessGame.startGame();
    }

    @Test
    @DisplayName("체스 게임을 시작하면 상태가 Ready 이다.")
    void start_game() {
        //given //when //then
        assertThat(chessGameDto.getGameState()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("체스 게임을 준비하면 체스 판이 초기화 된다.")
    void setup_game_board() {
        //given //when //then
        assertThat(chessGameDto.getBoard()).hasSize(64);
    }

    @Test
    @DisplayName("체스의 말을 움직이면 상태가 Playing 이다.")
    void move() {
        //given
        Position sourcePosition = Position.from("b2");
        Position targetPosition = Position.from("b4");

        //when
        chessGame.move(chessGameDto.from(sourcePosition), chessGameDto.from(targetPosition));

        //then
        assertThat(chessGame.getGameState()).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("체스 게임을 종료한 상태는 Finish 이다.")
    void end_game() {
        //given //when
        ChessGameDto chessGameDto = chessGame.endGame();

        //then
        assertThat(chessGameDto.getGameState()).isInstanceOf(Finish.class);
    }
}
