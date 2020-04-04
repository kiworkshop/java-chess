package chess;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessTeam;
import chess.domain.UserCommand;
import chess.dto.MovePositionDto;
import chess.presentation.ConsoleInput;
import chess.presentation.ConsoleOutput;

public class ChessGame {

    // Todo 보드 잘 만들어졌는지 확인
    private ChessBoard board = new ChessBoard();

    public void start() {
        ConsoleOutput.showIntro();

        showBoardIfStart();

        while (true) {
            String[] commandSplit = ConsoleInput.getUserCommandSplit();
            UserCommand userCommand = UserCommand.of(commandSplit);

            if (userCommand.isEnd()) {
                break;
            }

            if (userCommand.isNotMove() || UserCommand.isInvalidMoveCommand(commandSplit)) {
                continue;
            }

            try {
                MovePositionDto movePositionDto = createMovePositionDto(commandSplit);
                board.move(movePositionDto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            ChessTeam.changeTurn();
            ConsoleOutput.show(board.getDto());
        }
    }

    private void showBoardIfStart() {
        UserCommand userCommand = ConsoleInput.getUserCommand();
        if (userCommand.isStart()) {
            ConsoleOutput.show(board.getDto());
            return;
        }
        endProgram();
    }

    private void endProgram() {
        System.exit(0);
    }

    private MovePositionDto createMovePositionDto(String[] commandSplit) {
        String sourcePosition = commandSplit[1];
        String targetPosition = commandSplit[2];
        return MovePositionDto.of(ChessBoardPosition.from(sourcePosition), ChessBoardPosition.from(targetPosition));
    }
}
