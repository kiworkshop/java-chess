package chess.view;

import chess.domain.board.Scores;
import chess.dto.console.BoardConsoleDto;

public class ConsoleOutputView implements OutputView {

    private static final String HEADER = "> ";
    private static final String TURN_FORMAT = HEADER + "%s의 차례입니다.%n";
    private static final String WINNER_FORMAT = HEADER + "%s의 승리입니다. 축하합니다.%n";

    @Override
    public void printGuide() {
        System.out.println(HEADER + "체스 게임을 실행합니다.");
        System.out.println(HEADER + "게임 시작 : start");
        System.out.println(HEADER + "게임 종료 : end");
        System.out.println(HEADER + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void printBoard(final BoardConsoleDto boardConsoleDto) {
        boardConsoleDto.getPositionDtos()
                .forEach(positionDto -> {
                    System.out.print(positionDto.getName());
                    if (positionDto.isLastFile()) {
                        System.out.println();
                    }
                });
        System.out.println();
    }

    @Override
    public void printScores(final Scores scores) {
        System.out.println(HEADER + "WHITE 점수: " + scores.getWhiteScore());
        System.out.println(HEADER + "BLACK 점수: " + scores.getBlackScore());
    }

    @Override
    public void printTurn(String currentTurn) {
        System.out.printf(TURN_FORMAT, currentTurn);
    }

    @Override
    public void printWinner(String winnerName) {
        System.out.printf(WINNER_FORMAT, winnerName);
    }

    @Override
    public void printMessage(String message) {
        System.out.println(HEADER + message);
        System.out.println();
    }
}
