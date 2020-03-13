package chess.presentation;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;

import java.util.Map;

public class ConsoleOutput {

    public static void showIntro() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start ");
        System.out.println("> 게임 종료 : end ");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    // Todo
    public static void show(Map<ChessBoardPosition, ChessPiece> pieces) {
        // 보드판 보여주는 함수
    }
}
