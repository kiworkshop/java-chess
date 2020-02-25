package mychess.domain;

public class ChessGame {

    public void processCommand(String userCommand) {
        if (userCommand.equals("end")) end();
        if (userCommand.equals("start")) start();
        else throw new IllegalArgumentException("잘못된 명령어를 입력했습니다");
    }

    private void end() {
        System.exit(0);
    }

    private void start() {
        System.out.println("시작");
    }
}
