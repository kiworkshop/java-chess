package chess.domain.command;

import chess.domain.player.Position;

import java.util.List;

public class MoveOptions {

    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final Position source;
    private final Position target;

    public MoveOptions(final List<String> options) {
        this(Position.of(options.get(SOURCE_INDEX)), Position.of(options.get(TARGET_INDEX)));
    }

    public MoveOptions(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
