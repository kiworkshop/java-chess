package chess.domain.piece;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PiecePosition {
    private final File file;
    private final Rank rank;

    @Override
    public boolean equals(Object object) {
        if (object instanceof PiecePosition) {
            PiecePosition position = (PiecePosition) object;
            return this.getFile().equals(position.getFile()) && this.getRank().equals(position.getRank());
        }
        return false;
    }
}
