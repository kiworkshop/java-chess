package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class PiecePosition {
    private final File file;
    private final Rank rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PiecePosition)) {
            return false;
        }
        PiecePosition that = (PiecePosition) o;
        return getFile() == that.getFile() &&
                getRank() == that.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFile(), getRank());
    }
}
