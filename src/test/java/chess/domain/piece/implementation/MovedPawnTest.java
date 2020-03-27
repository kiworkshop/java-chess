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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovedPawnTest {

    private PieceState whiteMovedPawn;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto blackPieceDto = new PieceDto(EnumPieceType.PAWN, EnumTeam.BLACK);
    private PieceDto whitePieceDto = new PieceDto(EnumPieceType.PAWN, EnumTeam.WHITE);

    @BeforeEach
    void setUp() {
        whiteMovedPawn = MovedPawn.of(EnumPosition.from("B3"), EnumTeam.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardStateImpl.from(boardDto);
    }

    @Test
    @DisplayName("Pawn은 바로 앞에 기물이 있는 경우 전진할 수 없음")
    void moveToAlly() {
        boardDto.put(EnumPosition.from("B4"), whitePieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(EnumPosition.from("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        boardDto.put(EnumPosition.from("B4"), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(EnumPosition.from("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteMovedPawn.move(EnumPosition.from("B4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        boardDto.put(EnumPosition.from("C4"), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThat(whiteMovedPawn.move(EnumPosition.from("C4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"C4", "A4", "B2", "A1", "B3"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(EnumPosition.from(input), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("앞으로 전진시 진행 타겟에 적군이 있어 가지 못하는 경우")
    void moveToEnemyException() {
        boardDto.put(EnumPosition.from("B4"), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(EnumPosition.from("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }
}