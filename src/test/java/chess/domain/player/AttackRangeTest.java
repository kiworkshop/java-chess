package chess.domain.player;

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

class AttackRangeTest {

    @ParameterizedTest
    @ValueSource(strings = {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"})
    @DisplayName("공격 가능한 위치들을 표시한다.")
    void create(String key) {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces(Color.WHITE);
        AttackRange attackRange = new AttackRange(pieces);
        Position position = Position.of(key);

        //when
        boolean contains = attackRange.contains(position);

        //then
        assertThat(contains).isTrue();
    }

    @Test
    @DisplayName("공격 가능한 위치들을 갱신한다.")
    void update() {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces(Color.WHITE);
        AttackRange attackRange = new AttackRange(pieces);
        Position before = Position.of("b1");
        Position current = Position.of("c3");

        //when
        attackRange.update(before, current, new Knight(Color.WHITE));

        //then
        assertThat(attackRange.contains(Position.of("a3"))).isTrue();
        assertThat(attackRange.contains(Position.of("b1"))).isTrue();
        assertThat(attackRange.contains(Position.of("c3"))).isTrue();
        assertThat(attackRange.contains(Position.of("b5"))).isTrue();
        assertThat(attackRange.contains(Position.of("d5"))).isTrue();
    }
}
