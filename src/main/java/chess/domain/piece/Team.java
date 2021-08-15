package chess.domain.piece;

import lombok.Getter;

@Getter
public enum Team {
    BLACK(1),
    WHITE(2);

    private int marker;

    Team(int marker) {
        this.marker = marker;
    }

    public int getMarker() {
        return marker;
    }
}
