package chess.view;

import chess.dto.Scores;
import chess.dto.TurnDto;

import java.util.Map;

public class ConsoleOutputView implements OutputView {

    private static final String HEADER = "> ";
    private static final String TURN_FORMAT = HEADER + "%s팀의 %s 차례입니다.%n";
    private static final String WINNER_FORMAT = HEADER + "%s의 승리입니다. 축하합니다.%n";

    private static final int FILE = 0;
    private static final int RANK = 1;
    private static final String NEW_LINE = "\n";

    @Override
    public void printGuide() {
        System.out.println(HEADER + "체스 게임을 실행합니다.");
        System.out.println(HEADER + "게임 시작 : start");
        System.out.println(HEADER + "게임 종료 : end");
        System.out.println(HEADER + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void printBoard(final Map<String, String> boardDto) {
        StringBuilder sb = new StringBuilder();

        boardDto.keySet().stream()
                .sorted(this::comparePositionName)
                .forEach(positionName -> {
                    String pieceName = boardDto.get(positionName);
                    sb.append(pieceName);
                });

        insertNewLineAtTheEndOfEachRank(sb);

        System.out.println(sb);
    }

    private int comparePositionName(String o1, String o2) {
        if (o1.charAt(RANK) == o2.charAt(RANK)) {
            return Character.compare(o1.charAt(FILE), o2.charAt(FILE));
        }
        return Character.compare(o2.charAt(RANK), o1.charAt(RANK));
    }

    private void insertNewLineAtTheEndOfEachRank(StringBuilder sb) {
        for (int rank = 1; rank <= 7; rank++) {
            sb.insert(endOf(rank), NEW_LINE);
        }
    }

    private int endOf(int rank) {
        return rank * 8 + rank - 1;
    }

    @Override
    public void printScores(final Scores scores) {
        System.out.println(HEADER + "WHITE 점수: " + scores.getWhiteScore());
        System.out.println(HEADER + "BLACK 점수: " + scores.getBlackScore());
    }

    @Override
    public void printTurn(TurnDto currentTurn) {
        String team = currentTurn.getTeam();
        String playerName = currentTurn.getPlayerName();
        System.out.printf(TURN_FORMAT, team, playerName);
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
