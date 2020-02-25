package mychess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_GAME_START = "체스 게임을 시작합니다.";
    private static final String MESSAGE_GET_GAME_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public String getUserCommand() {
        System.out.println(MESSAGE_GAME_START);
        System.out.println(MESSAGE_GET_GAME_COMMAND);

        return SCANNER.nextLine();
    }
}
