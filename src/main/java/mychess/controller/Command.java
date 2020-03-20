package mychess.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Command {
    START("start", "체스 게임 시작하기"),
    END("end", "체스 게임 끝내기"),
    MOVE("move", "체스 말 움직이기 ex: move b1 b2"),
    UNKNOWN("", "처리할 수 없는 명령어입니다.");

    public static final String INSTRUCTION = getInstruction();

    private String keyword;
    private String description;

    Command(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    private static String getInstruction() {
        return Arrays.stream(values())
                .filter(command -> command != UNKNOWN)
                .map(command -> command.keyword + ": " + command.description)
                .collect(Collectors.joining("\n"));
    }
}
