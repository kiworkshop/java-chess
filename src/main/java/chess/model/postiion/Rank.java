package chess.model.postiion;

public interface Rank {

    Rank add(int value);

    boolean isMax();

    boolean isMin();

    int rankDifference(Rank rank);

    int value();
}
