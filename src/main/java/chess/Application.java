package chess;

import chess.domain.board.Board;
import chess.view.AnswerType;
import chess.view.OutputView;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작: start");
        System.out.println("게임 종료: end");
        System.out.println("게임 이동: move source위치 target위치 - 예: move b2 b3");

        Board board = null;
        while (true) {
            String answer = scanner.nextLine();
            String[] commands = answer.split(" ");
            AnswerType answerType = AnswerType.of(commands[0]);
            if (answerType == AnswerType.END) {
                break;
            }
            if (answerType == AnswerType.START) {
                board = new Board();
            }
            if (answerType == AnswerType.MOVE) {
                board.move(commands[1], commands[2]);
            }
            OutputView.printBoard(board);

        }

    }

}
