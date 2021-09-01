package chess.domain.board;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final int fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> hasSameIndex(fileIndex, file))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 파일이 존재하지 않습니다."));
    }

    private static boolean hasSameIndex(final int fileIndex, final File file) {
        return file.index == fileIndex;
    }

    public int calculateGap(final File file) {
        return this.index - file.index;
    }

    public File move(final int amount) {
        return File.of(this.index + amount);
    }

    public boolean canMove(final int amount) {
        int fileIndex = index + amount;

        return isInRange(fileIndex);
    }

    private boolean isInRange(final int fileIndex) {
        return fileIndex >= A.index && fileIndex <= H.index;
    }
}
