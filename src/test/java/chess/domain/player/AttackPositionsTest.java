package chess.domain.player;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class AttackPositionsTest {

    @Test
    @DisplayName("기물들이 주어지면 공격 가능한 위치들이 초기화된다.")
    void create() {
        //given
        Map<Position, Piece> pieces = PieceFactory.createPieces(Color.WHITE);

        //when
        AttackPositions attackPositions = new AttackPositions(pieces);

        //then

    }

}
