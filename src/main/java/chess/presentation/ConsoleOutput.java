package chess.presentation;

import chess.domain.ChessBoardSquare;
import chess.domain.ChessPiece;
import chess.domain.RowDto;
import chess.domain.SquareDto;

import java.util.List;
import java.util.Map;

public class ConsoleOutput {

    public static void showIntro() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start ");
        System.out.println("> 게임 종료 : end ");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    // Todo
    public static void show(Map<ChessBoardSquare, ChessPiece> pieces) {
        // 보드판 보여주는 함수
    }

    public static void show(RowDto rowDto) {
        List<SquareDto> squareDtos = rowDto.getRowDto();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               System.out.print(squareDtos.get(i * 8 + j).getUnicodeIfExists());
            }
            System.out.println(" ");
        }

    }
}
