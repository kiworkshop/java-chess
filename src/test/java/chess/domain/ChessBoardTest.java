package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("초기 체스판을 세팅한다.")
    void initialize() {
        //given
        //when
        ChessBoard chessBoard = new ChessBoard();

        //then
        Assertions.assertThat(chessBoard.size()).isEqualTo(64);
    }

    @Test
    @DisplayName("체스판 상의 위치를 확인한다.")
    void create() {
        //given
        String input = "a1";

        //when
        ChessBoard chessBoard = new ChessBoard();

        //then
        Assertions.assertThat(chessBoard.getPositions(input)).isEqualTo(new Position(File.A, Rank.ONE));
    }
}
