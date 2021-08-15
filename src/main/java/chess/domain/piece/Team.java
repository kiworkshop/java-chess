package chess.domain.piece;

import lombok.Getter;

@Getter
public enum Team {
BLACK(1, Rank.EIGHT, Rank.SEVEN),
    WHITE(2, Rank.ONE, Rank.TWO);

    private int marker;
    private Rank piecesRank;
    private Rank pawnRank;

    Team(int marker, Rank piecesRank, Rank pawnRank) {
        this.marker = marker;
        this.piecesRank = piecesRank;
        this.pawnRank = pawnRank;
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
