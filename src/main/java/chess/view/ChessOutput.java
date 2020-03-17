package chess.view;

import chess.controller.dto.ChessResponse;

public interface ChessOutput {
    void write(ChessResponse chessResponse);
}
