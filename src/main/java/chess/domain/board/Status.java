package chess.domain.board;

public class Status {
    private final double whiteScore;
    private final double blackScore;
    private final boolean isWhiteKingDead;
    private final boolean isBlackKingDead;

    public Status(final double whiteScore, final double blackScore, boolean isWhiteKingDead, boolean isBlackKingDead) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.isWhiteKingDead = isWhiteKingDead;
        this.isBlackKingDead = isBlackKingDead;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public boolean isWhiteKingDead() {
        return isWhiteKingDead;
    }

    public boolean isBlackKingDead() {
        return isBlackKingDead;
    }
}
