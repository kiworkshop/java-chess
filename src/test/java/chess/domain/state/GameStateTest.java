package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Team;
import chess.domain.game.Turn;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameStateTest {
    @Test
    @DisplayName("게임이 시작되지 않으면 체스 말을 움직일 수 없다.")
    void ready_state_moveAndToggleTurn() {
        //given
        Pawn pawn = Pawn.of(Team.WHITE, Position.from("c3"));
        Knight knight = Knight.of(Team.BLACK, Position.from("d4"));

        // when
        Ready ready = new Ready();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> ready.move(pawn, knight));
    }

    @Test
    @DisplayName("게임이 준비되지 않으면 종료할 수 없다.")
    void ready_state_end() {
        //given //when
        Ready ready = new Ready();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> ready.end());
    }

    @Test
    @DisplayName("게임이 준비되지 않으면 턴을 바꿀 수 없다.")
    void ready_state_turn() {
        //given //when
        Ready ready = new Ready();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> ready.getTurn());
    }

    @Test
    @DisplayName("게임이 시작되면 다시 게임을 시작할 수 없다.")
    void playing_state_start() {
        //given //when
        Playing playing = new Playing(Turn.of(Team.WHITE), Board.of(BoardInitializer.reset()));

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> playing.start());
    }

    @Test
    @DisplayName("게임이 종료되면 게임을 시작 수 없다.")
    void finish_state_start() {
        //given //when
        Finish finish = new Finish();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> finish.end());
    }

    @Test
    @DisplayName("게임이 종료되면 체스 말을 움직일 수 없다.")
    void finish_state_moveAndToggleTurn() {
        //given
        Pawn pawn = Pawn.of(Team.WHITE, Position.from("c3"));
        Knight knight = Knight.of(Team.BLACK, Position.from("d4"));

        // when
        Finish finish = new Finish();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> finish.move(pawn, knight));
    }

    @Test
    @DisplayName("게임이 종료되면 턴을 바꿀 수 없다.")
    void finish_state_end() {
        //given //when
        Finish finish = new Finish();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> finish.end());
    }

    @Test
    @DisplayName("게임이 종료되면 턴을 바꿀 수 없다.")
    void finish_state_turn() {
        //given //when
        Finish finish = new Finish();

        //then
        assertThrows(UnsupportedOperationException.class,
                () -> finish.getTurn());
    }
}
