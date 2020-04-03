package mychess.view;

import mychess.controller.dto.ChessResponse;

public interface ChessOutput {
    void write(ChessResponse chessResponse);
}
