package chess.domain.plate;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private String fileMarker;
    private int filePosition;

    File(String fileMarker, int filePosition) {
        this.fileMarker = fileMarker;
        this.filePosition = filePosition;
    }

    public static File findBy(String fileMarker) {
        return Arrays.stream(File.values())
                .filter(v -> v.fileMarker.equals(fileMarker))
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError("해당되는 위치가 없습니다."));
    }

    public static File findBy(int filePosition) {
        return Arrays.stream(File.values())
                .filter(v -> v.filePosition == filePosition)
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError("해당되는 위치가 없습니다."));
    }
}
