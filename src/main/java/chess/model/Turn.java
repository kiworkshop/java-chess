package chess.model;

public interface Turn {

    boolean sameTurn(Team team);

    void changeTurn();

}
