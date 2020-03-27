package chess.model.postiion;

public interface File {

    File add(int value);

    boolean isMax();

    boolean isMin();

    int fileDifference(File file);

    int value();

    String getFileName();
}
