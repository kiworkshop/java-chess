package chess.domain.piece;

import lombok.Getter;

@Getter
public enum Team {
    BLACK(1, Rank.EIGHT, Rank.SEVEN),
    WHITE(2, Rank.ONE, Rank.TWO);

    private int marker;
    private Rank piecesInitRank;
    private Rank pawnInitRank;

    Team(int marker, Rank piecesRank, Rank pawnInitRank) {
        this.marker = marker;
        this.piecesInitRank = piecesRank;
        this.pawnInitRank = pawnInitRank;
    }

    public String displayName(String name) {
        if (this.equals(BLACK)) {
            return name.toUpperCase();
        }
        if (this.equals(WHITE)) {
            return name.toLowerCase();
        }
        return name;
    }
}
