package chess.view;

import chess.controller.dto.ChessResponse;

public interface ChessOutput {
    void notifyChessStart();
    void showChessBoard(ChessResponse response);
    void notifyChessEnd();
}
