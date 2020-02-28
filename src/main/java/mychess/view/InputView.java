package mychess.view;

import mychess.domain.command.AbstractCommand;
import mychess.domain.command.Command;
import mychess.domain.command.InitialCommand;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_GAME_START = "> 체스 게임을 시작합니다.";
    private static final String MESSAGE_START_COMMAND = "> 게임 시작 : start";
    private static final String MESSAGE_END_COMMAND = "> 게임 종료 : end";
    private static final String MESSAGE_MOVE_COMMAND = "> 기물 이동 : move {source위치} {target위치} - 예시) move b2 b3";
    private static final String MESSAGE_GET_INPUT = "입력> ";

    public AbstractCommand getInitialCommand() {
        System.out.println(MESSAGE_GAME_START);
        System.out.println(MESSAGE_START_COMMAND);
        System.out.println(MESSAGE_END_COMMAND);
        System.out.println(MESSAGE_MOVE_COMMAND);
        System.out.println();

        return new InitialCommand(SCANNER.nextLine());
    }

    public AbstractCommand getPlayerCommand() {
        System.out.println();
        System.out.print(MESSAGE_GET_INPUT);

        return new Command(SCANNER.nextLine());
    }
}
