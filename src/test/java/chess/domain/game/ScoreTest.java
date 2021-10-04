package chess.domain.game;

import chess.domain.position.Position;
import chess.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    private ChessGame chessGame;

    @BeforeEach
    void before() {
        chessGame = ChessGame.of(new Ready());
    }

    @Test
    @DisplayName("체스 말의 점수를 계산한다.")
    void sum_white_piece_score() {
        //given
        chessGame = chessGame.start();
        movePosition();

        //when
        Score score = chessGame.status();
        double whiteScore = score.white();
        double blackScore = score.black();

        //then
        assertThat(whiteScore).isEqualTo(36.0);
        assertThat(blackScore).isEqualTo(34.0);
    }

    private void movePosition() {
        //white
        chessGame.moveAndToggleTurn(Position.from("b2"), Position.from("b4"));
        //black
        chessGame.moveAndToggleTurn(Position.from("c7"), Position.from("c5"));
        //white
        chessGame.moveAndToggleTurn(Position.from("b4"), Position.from("c5")); // black pawn dead
        //black
        chessGame.moveAndToggleTurn(Position.from("d7"), Position.from("d5"));
        //white
        chessGame.moveAndToggleTurn(Position.from("h2"), Position.from("h3"));
        //black
        chessGame.moveAndToggleTurn(Position.from("c8"), Position.from("h3")); // white pawn dead
        //white
        chessGame.moveAndToggleTurn(Position.from("g1"), Position.from("h3")); // black bishop dead
        //black
        chessGame.moveAndToggleTurn(Position.from("h7"), Position.from("h5"));
        //white
        chessGame.moveAndToggleTurn(Position.from("c2"), Position.from("c4")); // c2, c3 pawn same file
    }
}
