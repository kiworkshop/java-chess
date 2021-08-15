package chess.domain.piece;

import lombok.Getter;

import java.util.Locale;

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

    public String displayName(String name) {
        if (this.equals(BLACK)) {
            return name.toLowerCase();
        }
        if (this.equals(WHITE)) {
            return name.toUpperCase();
        }
        return name;
    }
}
