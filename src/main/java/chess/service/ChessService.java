package chess.service;

import chess.controller.dto.ChessResponse;
import chess.controller.dto.MoveParams;
import chess.domain.ChessGame;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.position.EnumFile;
import chess.domain.position.EnumPosition;
import chess.domain.position.EnumRank;
import chess.model.board.Board;
import chess.model.piece.PieceState;

public class ChessService {

    ChessGame chessGame;

    public ChessResponse start() {
        chessGame = new ChessGame(new EnumRepositoryBoardInitializer());
        String board = parseBoardToString();
        return new ChessResponse(board, "게임 시작");
    }

    public ChessResponse end() {
        return new ChessResponse(null, "게임 종료");
    }

    public ChessResponse move(MoveParams moveParams) {
        if (chessGame == null) {
            throw new IllegalArgumentException("아직 체스 게임을 시작하지 않았습니다.");
        }
        chessGame.move(moveParams);
        String board = parseBoardToString();
        return new ChessResponse(board, "체스 이동");
    }

    public String parseBoardToString() {
        Board board = chessGame.getChessBoard();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getColumnName());
        for (EnumRank rank : EnumRank.values()) {
            for (EnumFile file : EnumFile.values()) {
                PieceState pieceState = board.getPieceState(EnumPosition.from(file, rank));
                String movableString = pieceState == null ? " " : pieceState.getFigure();
                stringBuilder.append("[" + movableString + "]");
            }
            stringBuilder.append(" " + rank.value());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getColumnName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (EnumFile file : EnumFile.values()) {
            stringBuilder.append(" " + file.getFileName() + " ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
