package chess.view;

import chess.dto.console.BoardDto;
import chess.domain.board.Status;

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
    public void printBoard(final BoardDto boardDto) {
        boardDto.getPositionDtos()
                .forEach(positionDto -> {
                    System.out.print(positionDto.getName());
                    if (positionDto.isLastFile()) {
                        System.out.println();
                    }
                });
        System.out.println();
    }

    @Override
    public void printStatus(final Status status) {
        if (status.isWhiteKingDead()) {
            System.out.printf(WINNER_FORMAT, "BLACK");
            return;
        }

        if (status.isBlackKingDead()) {
            System.out.printf(WINNER_FORMAT, "WHITE");
            return;
        }

        System.out.println(HEADER + "WHITE 점수: " + status.getWhiteScore());
        System.out.println(HEADER + "BLACK 점수: " + status.getBlackScore());
    }

    @Override
    public void printTurn(boolean isWhiteTurn) {
        if (isWhiteTurn) {
            System.out.printf(TURN_FORMAT, "WHITE");
            return;
        }

        System.out.printf(TURN_FORMAT, "BLACK");
    }
}
