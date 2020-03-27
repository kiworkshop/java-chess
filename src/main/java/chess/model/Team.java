package chess.model;

public interface Team {

    boolean isAlly(Team team);

    boolean isEnemy(Team team);

    boolean isWhite();

    boolean isBlack();

}
