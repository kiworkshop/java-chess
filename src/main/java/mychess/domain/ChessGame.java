package mychess.domain;

public class ChessGame {

    private final Player playerWhite;
    private final Player playerBlack;
    private Board board;

    public ChessGame(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public void processCommand(String userCommand) {
        if (userCommand.equals("end")) end();
        if (userCommand.equals("start")) createBoard();
        else throw new IllegalArgumentException("잘못된 명령어를 입력했습니다");
    }

    private void end() {
        System.exit(0);
    }

    private void createBoard() {
        //this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }
}
