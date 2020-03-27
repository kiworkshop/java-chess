package chess.domain.piece;

import chess.domain.board.BoardStateImpl;
import chess.domain.player.EnumTeam;
import chess.domain.position.EnumPosition;
import chess.model.board.BoardState;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    private PieceState whiteBishop;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto blackPieceDto = new PieceDto(EnumPieceType.BISHOP, EnumTeam.BLACK);
    private PieceDto whitePieceDto = new PieceDto(EnumPieceType.BISHOP, EnumTeam.WHITE);

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.of(EnumPosition.from("C4"), EnumTeam.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardStateImpl.from(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "B3", "D5", "E6", "F7", "G8"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "B3", "D5", "E6", "F7", "G8"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        boardDto.put(EnumPosition.from(target), whitePieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A6:B5", "E6:D5", "G8:F7"}, delimiter = ':')
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath(String target, String path) {
        boardDto.put(EnumPosition.from(path), whitePieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "B3", "D5", "E6", "F7", "G8"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        boardDto.put(EnumPosition.from(target), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThat(whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2:B3", "A6:B5", "E6:D5", "G8:F7"}, delimiter = ':')
    @DisplayName("진행 경로에 적군이 있는 경우 이동 불가")
    void enemyOnPath(String target, String path) {
        boardDto.put(EnumPosition.from(path), blackPieceDto);
        boardState = BoardStateImpl.from(boardDto);
        assertThatThrownBy(() -> whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"E5"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String target) {
        assertThatThrownBy(() -> whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "B2", "D4"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(EnumPosition.from(target), blackPieceDto);
        assertThatThrownBy(() -> whiteBishop.move(EnumPosition.from(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

}