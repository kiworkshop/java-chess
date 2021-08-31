package chess.dto.console;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardConsoleDto {

    private List<PositionDto> positionDtos = new ArrayList<>();

    public static BoardConsoleDto of(Board board) {
        return new BoardConsoleDto(board);
    }

    private BoardConsoleDto(final Board board) {
        Arrays.stream(Rank.values()).forEach(rank -> Arrays.stream(
                File.values()).forEach(file -> addPositionDto(file, rank, board)));
    }

    private void addPositionDto(final File file, final Rank rank, final Board board) {
        Position position = Position.from(file, rank);

        if (board.isEmpty(position)) {
            PositionDto positionDto = new PositionDto(position.getFile());
            positionDtos.add(positionDto);
            return;
        }

        Piece piece = board.findBy(position);
        String name = PieceType.findNameBy(piece);
        PositionDto positionDto = new PositionDto(position.getFile(), name);
        positionDtos.add(positionDto);
    }

    public List<PositionDto> getPositionDtos() {
        return Collections.unmodifiableList(positionDtos);
    }
}
