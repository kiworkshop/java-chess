package chess.view;

import chess.controller.dto.BoardDto;

public class ConsoleOutputView implements OutputView {


    @Override
    public void printBoard(final BoardDto boardDto) {
        boardDto.getPositionDtos()
                .forEach(positionDto -> {
                    System.out.print(positionDto.getName());
                    if (positionDto.isLastFile()) {
                        System.out.println();
                    }
                });
    }
}
