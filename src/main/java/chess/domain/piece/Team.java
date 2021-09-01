package chess.domain.piece;

import lombok.Getter;

@Getter
public enum Team {
    BLACK(1, Rank.EIGHT, Rank.SEVEN, 1),
    WHITE(2, Rank.ONE, Rank.TWO,-1);

    private int marker;
    private Rank piecesInitRank;
    private Rank pawnInitRank;
    private int pawnDirection;

    Team(int marker, Rank piecesRank, Rank pawnInitRank, int pawnDirection) {
        this.marker = marker;
        this.piecesInitRank = piecesRank;
        this.pawnInitRank = pawnInitRank;
        this.pawnDirection = pawnDirection;
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
