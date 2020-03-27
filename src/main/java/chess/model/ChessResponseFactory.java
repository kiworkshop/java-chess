package chess.model;

import chess.controller.dto.ChessResponse;
import chess.model.domain.game.*;
import chess.model.domain.game.pieces.ChessPiece;
import chess.model.domain.game.pieces.PieceColor;
import chess.model.domain.game.pieces.PieceType;

public class ChessResponseFactory {
    public static ChessResponse getChessResponse(ChessResponse.Status status) {
        return ChessResponse.of(status);
    }

    public static ChessResponse getChessResponse(ChessErrorMessage errorMessage) {
        ChessResponse.Status status = getStatus(errorMessage);
        return ChessResponse.of(status);
    }

    public static ChessResponse getChessResponse(ChessGameSystem chessGameSystem, ChessErrorMessage errorMessage) {
        ChessResponse.Status status = getStatus(errorMessage);
        ChessResponse.Piece[][] pieces = getPieces(chessGameSystem.getChessBoard().getBoardSnapshot());
        ChessResponse.Turn turn = getTurn(chessGameSystem.getTurn());
        return ChessResponse.of(status, pieces, turn);
    }

    private static ChessResponse.Status getStatus(ChessErrorMessage errorMessage) {
        switch (errorMessage) {
            case SUCCESS:
                return ChessResponse.Status.SUCCESS;
            case WHITE_WIN:
                return ChessResponse.Status.WHITE_WIN;
            case BLACK_WIN:
                return ChessResponse.Status.BLACK_WIN;
            case ERROR_WHITE_TURN:
                return ChessResponse.Status.ERROR_WHITE_TURN;
            case ERROR_BLACK_TURN:
                return ChessResponse.Status.ERROR_BLACK_TURN;
            case ERROR_WRONG_MOVE:
                return ChessResponse.Status.ERROR_WRONG_MOVE;
            case ERROR_SOURCE_NOT_VALID:
                return ChessResponse.Status.ERROR_SOURCE_NOT_VALID;
            case ERROR_INPUT_OUT_OF_BOUND:
                return ChessResponse.Status.ERROR_INPUT_OUT_OF_BOUND;
            default:
                throw new IllegalStateException("Unexpected value: " + errorMessage);
        }
    }

    private static ChessResponse.Piece[][] getPieces(ChessBoardSnapshot boardSnapshot) {
        ChessResponse.Piece[][] pieces = new ChessResponse.Piece[ChessBoard.GRID_SIZE][ChessBoard.GRID_SIZE];
        for (int y = 0; y < ChessBoard.GRID_SIZE; y++) {
            for (int x = 0; x < ChessBoard.GRID_SIZE; x++) {
                ChessPiece piece = boardSnapshot.get(Position.of(x, y));
                if (piece == null) {
                    pieces[y][x] = ChessResponse.Piece.EMPTY;

                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KING) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_KING;
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.QUEEN) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_QUEEN;
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.ROOK) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_ROOK;
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KNIGHT) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_KNIGHT;
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.BISHOP) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_BISHOP;
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.PAWN) {
                    pieces[y][x] = ChessResponse.Piece.WHITE_PAWN;

                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KING) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_KING;
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.QUEEN) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_QUEEN;
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.ROOK) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_ROOK;
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KNIGHT) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_KNIGHT;
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.BISHOP) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_BISHOP;
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.PAWN) {
                    pieces[y][x] = ChessResponse.Piece.BLACK_PAWN;
                }
            }
        }
        return pieces;
    }

    private static ChessResponse.Turn getTurn(PieceColor turn) {
        switch (turn) {
            case WHITE:
                return ChessResponse.Turn.WHITE;
            case BLACK:
                return ChessResponse.Turn.BLACK;
            default:
                throw new IllegalStateException("Unexpected value: " + turn);
        }
    }
}
