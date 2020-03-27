package chess.domain.position;

import chess.model.postiion.File;

import java.util.Arrays;

public enum EnumFile implements File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    EnumFile(int value) {
        this.value = value;
    }

    @Override
    public int fileDifference(File targetFile) {
        return targetFile.value() - value();
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public File add(int value) {
        int newValue = this.value + value;
        return Arrays.stream(values()).filter(files -> files.value == newValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 positon입니다."));
    }

    @Override
    public boolean isMax() {
        return this == H;
    }

    @Override
    public boolean isMin() {
        return this == A;
    }

    @Override
    public String getFileName() {
        return this.toString();
    }
}
