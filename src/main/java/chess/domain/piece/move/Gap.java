package chess.domain.piece.move;

public class Gap {

    private final int fileGap;
    private final int rankGap;

    public Gap(int fileGap, int rankGap) {
        this.fileGap = fileGap;
        this.rankGap = rankGap;
    }

    public int file() {
        return fileGap;
    }

    public int rank() {
        return rankGap;
    }
}
