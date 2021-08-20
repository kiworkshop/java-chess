package chess.view;

import chess.controller.dto.BoardDto;

public class ConsoleOutputView implements OutputView {

    private static final String HEADER = "> ";

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
}
