package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AttackPositionsTest {

    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"})
    @DisplayName("기물들이 주어지면 공격 가능한 위치들이 초기화된다.")
    void create(String key) {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces(Color.WHITE);
        AttackPositions attackPositions = new AttackPositions(pieces);
        Position position = Position.of(key);

        //when
        boolean isEmpty = attackPositions.isEmpty(position);

        //then
        assertThat(isEmpty).isFalse();
    }

    @Test
    @DisplayName("시작 위치에서의 공격 가능한 위치들을 없애고, 도착 위치에서의 공격 가능한 위치를 추가한다.")
    void update() {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces(Color.WHITE);
        AttackPositions attackPositions = new AttackPositions(pieces);
        Position before = Position.of("b1");
        Position current = Position.of("c3");

        //when
        attackPositions.update(before, current, new Knight(Color.WHITE));

        //then
        assertThat(attackPositions.isEmpty(Position.of("a3"))).isFalse();
        assertThat(attackPositions.isEmpty(Position.of("b1"))).isFalse();
        assertThat(attackPositions.isEmpty(Position.of("c3"))).isFalse();
        assertThat(attackPositions.isEmpty(Position.of("b5"))).isFalse();
        assertThat(attackPositions.isEmpty(Position.of("d5"))).isFalse();
    }
}
