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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private PieceState whiteKing;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto blackPieceDto = new PieceDto(EnumPieceType.KING, EnumTeam.BLACK);
    private PieceDto whitePieceDto = new PieceDto(EnumPieceType.KING, EnumTeam.WHITE);

    @BeforeEach
    void setUp() {
        whiteKing = King.of(EnumPosition.from("C4"), EnumTeam.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardStateImpl.from(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteKing.move(EnumPosition.from(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        boardDto.put(EnumPosition.from(target), whitePieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteKing.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        boardDto.put(EnumPosition.from(target), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThat(whiteKing.move(EnumPosition.from(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteKing.move(EnumPosition.from(input), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없는 거리입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(EnumPosition.from(target), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteKing.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없는 거리입니다.");
    }
}