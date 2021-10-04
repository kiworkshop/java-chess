package chess.dto;

public class ScoreDto {
    private final double white;
    private final double black;

    public ScoreDto(double white, double black) {
        this.white = white;
        this.black = black;
    }

    public String white() {
        return "White : " + white;
    }

    public String black() {
        return "Black : " + black;
    }
}
