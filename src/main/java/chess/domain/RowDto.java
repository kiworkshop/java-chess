package chess.domain;

import java.util.List;

public class RowDto {

    private List<SquareDto> rowDto;

    public RowDto(List<SquareDto> rowDto) {
        this.rowDto = rowDto;
    }

    public static RowDto of(List<SquareDto> rowDto) {
        return new RowDto(rowDto);
    }

    public List<SquareDto> getRowDto() {
        return rowDto;
    }
}
