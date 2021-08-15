package chess.domain.piece;

import lombok.Getter;

@Getter
public enum File {
    A("a",1),
    B("b",2),
    C("c",3),
    D("d",4),
    E("e",5),
    F("f",6),
    G("g",7),
    H("h",8);

    private String fileMarker;
    private int filePosition;

    File(String fileMarker, int filePosition) {
        this.fileMarker = fileMarker;
        this.filePosition = filePosition;
    }
}
