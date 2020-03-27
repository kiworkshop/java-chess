package chess.domain.piece.implementation;

import chess.domain.board.BoardStateImpl;
import chess.domain.piece.EnumPieceType;
import chess.domain.piece.PieceDto;
import chess.domain.player.EnumTeam;
import chess.domain.position.EnumPosition;
import chess.model.board.BoardState;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private PieceState whiteKnight;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto blackPieceDto = new PieceDto(EnumPieceType.KNIGHT, EnumTeam.BLACK);
    private PieceDto whitePieceDto = new PieceDto(EnumPieceType.KNIGHT, EnumTeam.WHITE);

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.of(EnumPosition.from("b1"), EnumTeam.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardStateImpl.from(boardDto);
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(EnumPosition.from("C3"), whitePieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteKnight.move(EnumPosition.from("C3"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        boardDto.put(EnumPosition.from("C3"), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThat(whiteKnight.move(EnumPosition.from("C3"), boardState))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteKnight.move(EnumPosition.from("C3"), boardState))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteKnight.move(EnumPosition.from("C4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(EnumPosition.from("C4"), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteKnight.move(EnumPosition.from("C4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

}