package chess.domain.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;

import java.util.Map;

public class OutputView {
    private static final int FILE_SIZE = 8;

    private OutputView() {

    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(BoardDto boardDto) {
        Map<Position, Piece> board = boardDto.getBoard();
        for (Position position : board.keySet()) {
            if (position.getFileNumber() % FILE_SIZE == 1) {
                System.out.println();
            }
            System.out.print(board.get(position).symbol());
        }
    }

}
