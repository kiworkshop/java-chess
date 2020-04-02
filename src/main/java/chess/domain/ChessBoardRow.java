package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardRow {
    
    private List<ChessBoardSquare> boardRow = new ArrayList<>();

    public ChessBoardRow(List<ChessBoardSquare> boardRow) {
        this.boardRow = boardRow;
    }

    public List<SquareDto> toDto() {
        return boardRow.stream().map(ChessBoardSquare::toDto).collect(Collectors.toList());
    }
}
