package chess.domain.board;

import java.util.Arrays;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final int fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> hasSameIndex(fileIndex, file))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean hasSameIndex(final int fileIndex, final File file) {
        return file.getIndex() == fileIndex;
    }

    public int getIndex() {
        return index;
    }

    public int calculateGap(final File file) {
        return this.index - file.index;
    }

    public File add(final int amount) {
        return File.of(this.index + amount);
    }
}
