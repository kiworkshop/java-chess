package chess.service;

import chess.controller.dto.ChessResponse;
import chess.controller.dto.MoveParams;
import chess.domain.ChessGame;

public class ChessService {

    ChessGame chessGame;

    public ChessResponse start() {
        chessGame = new ChessGame();
//        String board = parseBoardToString();
        return new ChessResponse(null, "게임 시작");
    }

    public ChessResponse end() {
        return new ChessResponse(null, "게임 종료");
    }

    public void move(MoveParams moveParams) {
        if (chessGame == null) {
            throw new IllegalArgumentException("아직 체스 게임을 시작하지 않았습니다.");
        }
        chessGame.move(moveParams);
    }

//    public String parseBoardToString() {
//        ChessBoard board = chessGame.getChessBoard();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(getColumnName());
//        for (Row row : Row.values()) {
//            for (Column column : Column.values()) {
//                PieceState pieceState = board.getMovable(column.getName() + row.getValue());
//                String movableString = pieceState.toString();
//                stringBuilder.append(movableString);
//            }
//            stringBuilder.append(" " + row.getValue());
//            stringBuilder.append("\n");
//        }
//        return stringBuilder.toString();
//    }
//
//    private String getColumnName() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Column column : Column.values()) {
//            stringBuilder.append(" " + column.getName() + " ");
//        }
//        stringBuilder.append("\n");
//        return stringBuilder.toString();
//    }
}
