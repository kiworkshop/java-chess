package chess.model;

import chess.controller.dto.ChessRequest;
import chess.controller.dto.ChessResponse;
import chess.model.domain.game.*;

public class ChessService {
    private ChessGameSystem chessGameSystem = null;

    public ChessResponse start() {
        chessGameSystem = new ChessGameSystem();
        chessGameSystem.start();
        return ChessResponseFactory.getChessResponse(chessGameSystem, ChessErrorMessage.SUCCESS);
    }

    public ChessResponse end() {
        chessGameSystem = null;
        return ChessResponseFactory.getChessResponse(ChessErrorMessage.SUCCESS);
    }

    public ChessResponse move(ChessRequest request) {
        if (chessGameSystem == null) {
            return ChessResponseFactory.getChessResponse(ChessResponse.Status.ERROR_GAME_NOT_STARTED);
        }

        ChessErrorMessage errorMessage = chessGameSystem.move(request.getSourcePosition(), request.getDestinationPosition());

        if (errorMessage == ChessErrorMessage.WHITE_WIN || errorMessage == ChessErrorMessage.BLACK_WIN) {
            ChessGameSystem tmpChessGameSystem = chessGameSystem;
            chessGameSystem = null;
            return ChessResponseFactory.getChessResponse(tmpChessGameSystem, errorMessage);
        }

        return ChessResponseFactory.getChessResponse(chessGameSystem, errorMessage);
    }
}
