package chess.view;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;

import java.util.List;

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

    public static void printChessBoard(ChessGameDto chessGameDto) {
        Board board = chessGameDto.board();
        System.out.println("------------------------");
        board.values().keySet()
                .forEach(position -> {
                    System.out.print(board.from(position).getSymbol());
                    separateLine(position);
                });
        System.out.println("------------------------");
    }

    private static void separateLine(Position position) {
        if (position.getFileNumber() / FILE_SIZE == 1) {
            System.out.println();
        }
    }

    public static void printScore(ScoreDto scoreDto) {
        System.out.println(scoreDto.white());
        System.out.println(scoreDto.black());
    }

    public static void printWinner(List<String> winner) {
        System.out.println("** 우승자 **");
        winner.forEach(System.out::println);
    }
}
