package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final Map<Integer, File> FILES = createFiles();

    private final int index;

    File(final int index) {
        this.index = index;
    }

    private static Map<Integer, File> createFiles() {
        HashMap<Integer, File> files = new HashMap<>();

        Arrays.stream(values())
                .forEach(file -> files.put(file.index, file));

        return Collections.unmodifiableMap(files);
    }

    public static File of(final int fileIndex) {
        File file = FILES.get(fileIndex);

        if (file == null) {
            throw new IllegalArgumentException("일치하는 파일이 존재하지 않습니다.");
        }

        return file;
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
