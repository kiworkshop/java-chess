package chess.controller.dto;

import chess.domain.board.File;

public class PositionDto {

    private static final String DEFAULT_NAME = ".";

    private final boolean isLastFile;
    private final String name;

    public PositionDto(final File file, final String name) {
        this.isLastFile = (file == File.H);
        this.name = name;
    }

    public PositionDto(final File file) {
        this(file, DEFAULT_NAME);
    }

    public boolean isLastFile() {
        return isLastFile;
    }

    public String getName() {
        return name;
    }
}
